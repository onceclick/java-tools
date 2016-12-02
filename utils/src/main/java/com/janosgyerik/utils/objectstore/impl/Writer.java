package com.janosgyerik.utils.objectstore.impl;

import java.io.OutputStream;
import java.util.function.BiConsumer;

public interface Writer<V> extends BiConsumer<OutputStream, V> {
}
