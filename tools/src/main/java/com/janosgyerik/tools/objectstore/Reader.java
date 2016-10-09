package com.janosgyerik.tools.objectstore;

import java.io.IOException;
import java.io.InputStream;

public interface Reader<V> {
    V parseFrom(InputStream in) throws IOException;
}
