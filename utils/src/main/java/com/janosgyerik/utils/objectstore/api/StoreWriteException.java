package com.janosgyerik.utils.objectstore.api;

import java.io.IOException;

public class StoreWriteException extends RuntimeException {
  public StoreWriteException(IOException e) {
    super(e);
  }
}
