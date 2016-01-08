package com.janosgyerik.tools.algorithm.tree.binarytree;

import java.util.Collection;

public interface BinarySearchTree<T extends Comparable<T>> {

    void insert(T value);

    void insertAll(Collection<T> value);

    boolean contains(T value);

    void delete(T value);
}
