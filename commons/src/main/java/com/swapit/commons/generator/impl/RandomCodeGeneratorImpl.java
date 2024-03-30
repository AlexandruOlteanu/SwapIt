package com.swapit.commons.generator.impl;

import com.swapit.commons.generator.RandomCodeGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomCodeGeneratorImpl implements RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String generateRandomCode(int length) {
        return IntStream.range(0, length)
                .map(i -> CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())))
                .mapToObj(c -> String.valueOf((char)c))
                .collect(Collectors.joining());
    }
}
