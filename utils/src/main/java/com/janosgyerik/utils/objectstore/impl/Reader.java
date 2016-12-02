package com.janosgyerik.utils.objectstore.impl;

import java.io.InputStream;
import java.util.function.Function;

public interface Reader<V> extends Function<InputStream, V> {
}
