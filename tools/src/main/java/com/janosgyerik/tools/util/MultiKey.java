package com.janosgyerik.tools.util;

import java.util.Arrays;

public class MultiKey {

    private final Object[] keys;
    private final int hashCode;

    public MultiKey(Object... keys) {
        this.keys = keys;
        this.hashCode = Arrays.hashCode(keys);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MultiKey) {
            MultiKey other = (MultiKey) obj;
            return Arrays.equals(keys, other.keys);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "MultiKey{keys=" + Arrays.toString(keys) + '}';
    }
}
