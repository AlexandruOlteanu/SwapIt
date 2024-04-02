package com.swapit.searchEngine.service.impl;

import com.swapit.commons.utils.Pair;
import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.searchEngine.api.service.dto.CategoryTreeValueDTO;
import com.swapit.searchEngine.service.ExternalOperationsService;
import com.swapit.searchEngine.service.ProductCategorizeService;
import com.swapit.searchEngine.service.SearchDictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchDictionaryServiceImpl implements SearchDictionaryService {

    @Qualifier("standardAnalyzer")
    private final Analyzer standardAnalyzer;
    private final Directory directory;
    @Value("${searchEngine.nrSearchResults}")
    private Integer nrSearchResults;
    private final ExternalOperationsService externalOperationsService;

    private final ProductCategorizeService productCategorizeService;
    private final static String PRODUCT_ID_KEY = "productId";
    private final static String METADATA_KEY = "metadata";

    @Override
    public void addProductInSearchDictionary(Integer productId) throws IOException {
        ProductDTO productDTO = externalOperationsService.getProductById(productId);
        assert productDTO != null;
        List<String> categories = productCategorizeService.getCategoryTree(productDTO.getCategoryId()).getParentCategories().stream()
                .map(CategoryTreeValueDTO::getValue).toList();
        String categoryChain = getCategoryChain(categories);
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        Document document = new Document();
        document.add(new TextField(PRODUCT_ID_KEY, String.valueOf(productDTO.getProductId()), Field.Store.YES));
        document.add(new TextField(METADATA_KEY, joinValues(productDTO.getTitle(), productDTO.getDescription(), categoryChain), Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Override
    public void updateProductInSearchDictionary(Integer productId) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        String documentId = String.valueOf(productId);
        Term term = new Term(PRODUCT_ID_KEY, documentId);
        Query query = new TermQuery(term);
        indexWriter.deleteDocuments(query);
        indexWriter.close();
        addProductInSearchDictionary(productId);
    }

    @Override
    public void deleteProductFromSearchDictionary(Integer productId) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        String documentId = String.valueOf(productId);
        Term term = new Term(PRODUCT_ID_KEY, documentId);
        Query query = new TermQuery(term);
        indexWriter.deleteDocuments(query);
        indexWriter.close();
    }

    @Override
    public List<Pair<Integer, Integer>> searchMatchingProductsScore(String query) throws IOException {
        List<String> queryValues = processQuery(query);
        Map<Integer, Integer> productScores = new HashMap<>();
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(directoryReader);

        queryValues.forEach(currentQueryValue -> {
                    try {
                        Term term = new Term(METADATA_KEY, currentQueryValue);
                        Query curQuery = new FuzzyQuery(term);
                        TopDocs results = searcher.search(curQuery, nrSearchResults);
                        for (ScoreDoc scoreDoc : results.scoreDocs) {
                            StoredFields storedFields = searcher.storedFields();
                            Document doc = storedFields.document(scoreDoc.doc);
                            Integer productId = Integer.valueOf(doc.get(PRODUCT_ID_KEY));
                            productScores.put(productId, productScores.getOrDefault(productId, 0) + 1);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        List<Pair<Integer, Integer>> productScoresList = productScores.entrySet().stream()
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing((Pair<Integer, Integer> pair) -> pair.getSecond()).reversed())
                .toList();

        directoryReader.close();
        return productScoresList;
    }

    private String joinValues(String title, String description, String categoryChain) {
        return Stream.of(title, description, categoryChain).filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    private String getCategoryChain(List<String> categories) {
        return categories.stream().filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    private List<String> processQuery(String query) {
        String[] words = query.split("[^a-zA-Z]+");
        return Arrays.asList(words);
    }
}
