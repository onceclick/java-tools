package com.janosgyerik.utils.objectstore;

import java.io.IOException;
import java.io.InputStream;

public interface Reader<V> {
    V parseFrom(InputStream in) throws IOException;
}
