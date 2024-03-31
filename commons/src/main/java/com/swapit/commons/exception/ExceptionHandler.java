package com.swapit.commons.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String X_ERROR_CODE = "x_error_code";
    private static final String X_ERROR_MESSAGE = "x_error_message";
    @org.springframework.web.bind.annotation.ExceptionHandler({ Throwable.class })
    public ResponseEntity<Object> handleMicroserviceThrownErrors(Throwable ex) {
        HttpHeaders headers = new HttpHeaders();
        MicroserviceException microserviceException;
        String errorCode = null, errorMessage = null;
        HttpStatus httpStatus = null;
        if (ex instanceof CompletionException) {
            ex = ex.getCause();
        }
        if (ex instanceof MicroserviceException mex) {
            headers.add(X_ERROR_CODE, mex.getErrorCode());
            headers.add(X_ERROR_MESSAGE, mex.getMessage());
            errorCode = mex.getErrorCode();
            errorMessage = mex.getErrorMessage();
            httpStatus = mex.getHttpStatus();
        }
        if (ex instanceof FeignException feignException) {
            Map<String, Collection<String>> responseHeaders = feignException.responseHeaders();
            if (responseHeaders.containsKey(X_ERROR_CODE)) {
                errorCode = responseHeaders.get(X_ERROR_CODE).iterator().next();
                headers.add(X_ERROR_CODE, errorCode);
            }
            if (responseHeaders.containsKey(X_ERROR_MESSAGE)) {
                errorMessage = responseHeaders.get(X_ERROR_MESSAGE).iterator().next();
                headers.add(X_ERROR_MESSAGE, errorMessage);
            }
            httpStatus = HttpStatus.resolve(feignException.status()) != null ? HttpStatus.resolve(feignException.status()) : HttpStatus.INTERNAL_SERVER_ERROR;
            assert httpStatus != null;
        }
        if (ex instanceof RestClientResponseException restClientResponseException) {
            headers = restClientResponseException.getResponseHeaders();
            assert headers != null;
            if (headers.containsKey(X_ERROR_CODE)) {
                errorCode = Objects.requireNonNull(headers.get(X_ERROR_CODE)).getFirst();
            }
            if (headers.containsKey(X_ERROR_MESSAGE)) {
                errorMessage = Objects.requireNonNull(headers.get(X_ERROR_MESSAGE)).getFirst();
            }
            restClientResponseException.getStatusCode();
            httpStatus = (HttpStatus) restClientResponseException.getStatusCode();
        }
        if (!headers.containsKey(X_ERROR_CODE)) {
            headers.add(X_ERROR_CODE, "-1");
            errorMessage = ex.toString();
            headers.add(X_ERROR_MESSAGE, errorMessage);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        microserviceException = new MicroserviceException(errorCode, errorMessage, httpStatus);
        microserviceException.setStackTrace(ex.getStackTrace());
        log.error("Error: {}", ex.getMessage(), microserviceException);
        assert httpStatus != null;
        return new ResponseEntity<>(headers, httpStatus);
    }
}
