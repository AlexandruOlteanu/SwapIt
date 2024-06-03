package com.swapit.searchEngine.service;

import com.swapit.commons.utils.Pair;
import com.swapit.searchEngine.util.SearchPagination;

import java.io.IOException;
import java.util.List;

public interface SearchDictionaryService {
    void addProductInSearchDictionary(Integer productId) throws IOException;
    void updateProductInSearchDictionary(Integer productId) throws IOException;
    void deleteProductFromSearchDictionary(Integer productId) throws IOException;
    SearchPagination searchMatchingProducts(String query, Integer pageNumber, Integer pageSize, String sortCriteria) throws IOException;
}
