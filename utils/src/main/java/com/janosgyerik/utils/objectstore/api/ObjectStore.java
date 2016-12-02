package com.janosgyerik.utils.objectstore.api;

import java.util.Optional;

/**
 * Store key-value pairs in some backend.
 *
 * @param <K> type of keys
 * @param <V> type of values
 */
public interface ObjectStore<K, V> {

    void write(K key, V value);

    Optional<V> read(K key);
}
