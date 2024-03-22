package com.swapit.searchEngine.service;

import com.swapit.commons.utils.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SearchIndexService {
    void indexProduct(Integer productId) throws IOException;
    List<Pair<Integer, Integer>> searchMatchingProductsScore(String query) throws IOException;
}
