package com.github.maiky1304.auxilium.misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Simple class to store two objects
 * @param <A>
 * @param <B>
 */
@RequiredArgsConstructor
@Getter
public class Pair<A, B> {

    private final A key;
    private final B value;

}
