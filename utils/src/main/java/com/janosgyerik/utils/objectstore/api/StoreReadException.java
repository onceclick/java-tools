package com.janosgyerik.utils.objectstore.api;

import java.io.IOException;

public class StoreReadException extends RuntimeException {
    public StoreReadException(IOException e) {
        super(e);
    }
}
