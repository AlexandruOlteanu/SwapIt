package com.swapit.searchEngine.service;

import java.io.IOException;
import java.util.Set;

public interface SearchIndexService {
    void indexProduct(Integer productId) throws IOException;
    Set<Integer> searchMatchingProductsIds(String query) throws IOException;
}
