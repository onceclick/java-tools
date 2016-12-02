package com.janosgyerik.utils.objectstore.api;

import java.io.IOException;
import java.util.Optional;

/**
 * Store key-value pairs in some backend.
 *
 * @param <K> type of keys
 * @param <V> type of values
 */
public interface ObjectStore<K, V> {

    void write(K key, V value) throws StoreWriteException;

    Optional<V> read(K key) throws StoreReadException;
}
