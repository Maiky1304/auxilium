package com.github.maiky1304.auxilium.misc;

import lombok.Getter;

/**
 * Extension of Pair to allow three variables
 * @param <A>
 * @param <B>
 * @param <C>
 */
@Getter
public class TriPair<A, B, C> extends Pair<A, B> {

    private final C secondValue;

    public TriPair(A key, B value, C secondValue) {
        super(key, value);
        this.secondValue = secondValue;
    }

}
