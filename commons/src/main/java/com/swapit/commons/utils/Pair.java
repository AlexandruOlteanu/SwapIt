package com.swapit.commons.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pair<U, V> {
    private final U first;
    private final V second;

    private Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <U, V> Pair<U, V> of(U a, V b) {
        return new Pair<>(a, b);
    }
}
