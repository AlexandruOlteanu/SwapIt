package com.swapit.commons.exception;

public enum ExceptionType {

    // Security Exceptions
    UNAUTHORIZED_ACTION,

    // User Exceptions
    USER_NOT_FOUND,
    USERNAME_ALREADY_EXISTS,
    EMAIL_ALREADY_EXISTS,
    WRONG_SECURITY_CODE,
    INVALID_USER_UPDATE_FIELD,
    WRONG_USERNAME_OR_PASSWORD,
    WRONG_PASSWORD,
    USER_BANNED,

    // Chat Exceptions
    CONVERSATION_NOT_FOUND,

    // Product Exceptions
    PRODUCT_NOT_FOUND,

    // Search Engine Exceptions
    PARENT_CATEGORY_NOT_FOUND,
    PRODUCT_CATEGORY_ALREADY_EXISTS,
    PRODUCT_CATEGORY_NOT_FOUND
}
