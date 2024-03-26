package com.swapit.apiGateway.error;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {

    private static final Integer INTERNAL_SERVER_ERROR = 500;
    private static final String X_ERROR_MESSAGE = "x_error_message";

    public static void handleException(Exception e, HttpServletResponse response) {
        response.setStatus(INTERNAL_SERVER_ERROR);
        response.setHeader(X_ERROR_MESSAGE, e.getMessage());
    }

}
