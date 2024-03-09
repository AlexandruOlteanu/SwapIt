package com.swapit.commons.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ThreadLocalContext {
    private static final ThreadLocal<Map<String, String>> context = new ThreadLocal<>();

    public static void setContext(Map<String, String> headers) {
        context.set(headers);
    }

    public static Map<String, String> getContext() {
        return context.get();
    }

    public static void removeContext() {
        context.remove();
    }

}
