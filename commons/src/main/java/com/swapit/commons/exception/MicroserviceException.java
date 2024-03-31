package com.swapit.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class MicroserviceException extends RuntimeException{
    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public MicroserviceException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
