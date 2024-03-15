package com.swapit.commons.cache;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CacheKeyGenerator {
    public static String generateKey(Object... keys) {
        return String.join("_", Arrays.stream(keys)
                .map(Object::toString)
                .toArray(String[]::new));
    }
}
