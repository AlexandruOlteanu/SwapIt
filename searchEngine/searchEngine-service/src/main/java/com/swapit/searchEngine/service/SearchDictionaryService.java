package com.swapit.searchEngine.service;

import com.swapit.commons.utils.Pair;

import java.io.IOException;
import java.util.List;

public interface SearchDictionaryService {
    void addProductInSearchDictionary(Integer productId) throws IOException;
    void updateProductInSearchDictionary(Integer productId) throws IOException;
    void deleteProductFromSearchDictionary(Integer productId) throws IOException;
    List<Pair<Integer, Integer>> searchMatchingProductsScore(String query) throws IOException;
}
