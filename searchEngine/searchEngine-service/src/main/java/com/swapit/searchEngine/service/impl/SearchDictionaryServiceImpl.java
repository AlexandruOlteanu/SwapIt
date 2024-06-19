package com.swapit.searchEngine.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.searchEngine.api.service.dto.CategoryTreeValueDTO;
import com.swapit.searchEngine.service.ExternalOperationsService;
import com.swapit.searchEngine.service.ProductCategorizeService;
import com.swapit.searchEngine.service.SearchDictionaryService;
import com.swapit.searchEngine.util.SearchPagination;
import com.swapit.searchEngine.util.SearchSortCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final ExternalOperationsService externalOperationsService;
    private final ProductCategorizeService productCategorizeService;

    private static final String PRODUCT_ID_KEY = "productId";
    private static final String METADATA_KEY = "metadata";
    private static final String CREATION_DATE_KEY = "creationDate";
    private static final String POPULARITY_KEY = "popularity";

    @Override
    public void addProductInSearchDictionary(Integer productId) throws IOException {
        ProductDTO productDTO = externalOperationsService.getProductById(productId);
        assert productDTO != null;
        List<String> categories = productCategorizeService.getCategoryTree(productDTO.getCategoryId()).getParentCategories().stream()
                .map(CategoryTreeValueDTO::getValue).toList();
        String categoryChain = getCategoryChain(categories);
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);

        try (IndexWriter indexWriter = new IndexWriter(directory, config)) {
            Document document = new Document();
            document.add(new TextField(PRODUCT_ID_KEY, String.valueOf(productDTO.getProductId()), Field.Store.YES));
            document.add(new TextField(METADATA_KEY, joinValues(productDTO.getTitle(), productDTO.getDescription(), categoryChain), Field.Store.YES));
            document.add(new LongPoint(CREATION_DATE_KEY, productDTO.getCreationDate().toInstant().toEpochMilli()));
            document.add(new StoredField(CREATION_DATE_KEY, productDTO.getCreationDate().toInstant().toEpochMilli()));
            document.add(new NumericDocValuesField(CREATION_DATE_KEY, productDTO.getCreationDate().toInstant().toEpochMilli()));
            document.add(new IntPoint(POPULARITY_KEY, productDTO.getPopularity()));
            document.add(new StoredField(POPULARITY_KEY, productDTO.getPopularity()));
            document.add(new NumericDocValuesField(POPULARITY_KEY, productDTO.getPopularity()));
            indexWriter.addDocument(document);
        }
    }

    @Override
    public void updateProductInSearchDictionary(Integer productId) throws IOException {
        deleteProductFromSearchDictionary(productId);
        addProductInSearchDictionary(productId);
    }

    @Override
    public void deleteProductFromSearchDictionary(Integer productId) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);
        try (IndexWriter indexWriter = new IndexWriter(directory, config)) {
            String documentId = String.valueOf(productId);
            Term term = new Term(PRODUCT_ID_KEY, documentId);
            Query query = new TermQuery(term);
            indexWriter.deleteDocuments(query);
        }
    }

    @Override
    public SearchPagination searchMatchingProducts(String query, Integer pageNumber, Integer pageSize, String sortCriteria) throws IOException {
        List<String> queryValues = processQuery(query);
        try (DirectoryReader directoryReader = DirectoryReader.open(directory)) {
            IndexSearcher searcher = new IndexSearcher(directoryReader);

            BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
            queryValues.forEach(currentQueryValue -> {
                Term term = new Term(METADATA_KEY, currentQueryValue);
                Query curQuery = new FuzzyQuery(term);
                booleanQuery.add(curQuery, BooleanClause.Occur.SHOULD);
            });

            Query finalQuery = booleanQuery.build();
            Sort sort = determineSortCriteria(sortCriteria);

            int start = pageNumber * pageSize;
            ScoreDoc lastScoreDoc = getLastScoreDoc(searcher, finalQuery, sort, start);

            TopDocs results = searcher.searchAfter(lastScoreDoc, finalQuery, pageSize, sort);

            List<Integer> productScoresList = new ArrayList<>();

            for (ScoreDoc scoreDoc : results.scoreDocs) {
                StoredFields storedFields = searcher.storedFields();
                Document doc = storedFields.document(scoreDoc.doc);
                Integer productId = Integer.valueOf(doc.get(PRODUCT_ID_KEY));
                productScoresList.add(productId);
            }

            if (sortCriteria.equalsIgnoreCase(SearchSortCriteria.RANDOM.name())) {
                Collections.shuffle(productScoresList);
            }

            int totalHits = Math.toIntExact(searcher.count(finalQuery));
            int totalNumberOfPages = (int) Math.ceil((double) totalHits / pageSize);

            return SearchPagination.builder()
                    .productIds(productScoresList)
                    .totalNumberOfPages(totalNumberOfPages)
                    .pageNumber(pageNumber)
                    .build();
        }
    }

    private Sort determineSortCriteria(String sortCriteria) {
        Sort sort;

        if (sortCriteria.equalsIgnoreCase(SearchSortCriteria.NEWEST.name())) {
            sort = new Sort(new SortField(CREATION_DATE_KEY, SortField.Type.LONG, true));
        } else if (sortCriteria.equalsIgnoreCase(SearchSortCriteria.POPULARITY.name())) {
            sort = new Sort(new SortField(POPULARITY_KEY, SortField.Type.INT, true));
        } else {
            sort = Sort.RELEVANCE;
        }

        return sort;
    }

    private ScoreDoc getLastScoreDoc(IndexSearcher searcher, Query query, Sort sort, int start) throws IOException {
        if (start == 0) return null;
        TopDocs topDocs = searcher.search(query, start, sort);
        return topDocs.scoreDocs.length == 0 ? null : topDocs.scoreDocs[topDocs.scoreDocs.length - 1];
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
        String lowerCaseQuery = query.toLowerCase();
        String[] words = lowerCaseQuery.split("[^a-zA-Z]+");
        return Arrays.asList(words);
    }
}
