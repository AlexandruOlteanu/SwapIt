package com.swapit.chat.util;

public class ChatCommonFunctions {
    public static String getSortingCriteria(String criteria) {
        ConversationSortCriteria productSortCriteria = ConversationSortCriteria.valueOf(criteria.toUpperCase());
        return switch (productSortCriteria) {
            case NEWEST -> "lastActionAt";
        };
    }
}
