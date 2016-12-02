package com.janosgyerik.utils.objectstore.impl;

import com.janosgyerik.utils.objectstore.api.ObjectStore;
import com.janosgyerik.utils.objectstore.api.StoreReadException;
import com.janosgyerik.utils.objectstore.api.StoreWriteException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class SimpleObjectStore<K, V> implements ObjectStore<K, V> {

    private final Map<K, Path> index = new HashMap<>();

    private final Iterator<Path> pathGenerator;
    private final Reader<V> reader;
    private final Writer<V> writer;

    public SimpleObjectStore(Iterator<Path> pathGenerator, Reader<V> reader, Writer<V> writer) {
        this.pathGenerator = pathGenerator;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Optional<V> read(K key) throws StoreReadException {
        try {
            return internalRead(key);
        } catch (IOException e) {
            throw new StoreReadException(e);
        }
    }

    private Optional<V> internalRead(K key) throws IOException {
        Path path = index.get(key);
        if (path == null) {
            return Optional.empty();
        }
        try (InputStream in = Files.newInputStream(path)) {
            return Optional.of(reader.parseFrom(in));
        }
    }

    @Override
    public void write(K key, V value) throws StoreWriteException {
        try {
            internalWrite(key, value);
        } catch (IOException e) {
            throw new StoreWriteException(e);
        }
    }

    private void internalWrite(K key, V value) throws IOException {
        Path path = index.get(key);
        if (path == null) {
            path = pathGenerator.next();
            index.put(key, path);

            File parentFile = path.toFile().getParentFile();
            if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                throw new IOException("could not create directory: " + parentFile);
            }
        }
        try (OutputStream out = Files.newOutputStream(path)) {
            writer.writeTo(out, value);
        }
    }
}
