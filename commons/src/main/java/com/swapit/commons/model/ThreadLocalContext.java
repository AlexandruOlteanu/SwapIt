package com.swapit.commons.model;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ThreadLocalContext {
    private static final ThreadLocal<Map<String, String>> externalContext = new ThreadLocal<>();

    public static void setExternalContext(Map<String, String> headers) {
        externalContext.set(headers);
    }

    public static Map<String, String> getExternalContext() {
        return externalContext.get();
    }

    public static void removeContext() {
        externalContext.remove();
    }

}
