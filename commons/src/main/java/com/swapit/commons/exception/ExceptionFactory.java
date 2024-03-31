package com.swapit.commons.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionFactory {

    private final Environment environment;

    public MicroserviceException create(ExceptionType exceptionType) {
        String property = "exception.application." + exceptionType.name().toLowerCase();
        String code = environment.resolvePlaceholders("${" + property + ".code}");
        String message = environment.resolvePlaceholders("${" + property + ".message}");
        int status = Integer.parseInt(environment.resolvePlaceholders("${" + property + ".status}"));
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        return new MicroserviceException(code, message, httpStatus);
    }
}