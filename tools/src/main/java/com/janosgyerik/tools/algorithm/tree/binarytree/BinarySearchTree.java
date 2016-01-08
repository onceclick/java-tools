package com.janosgyerik.tools.algorithm.tree.binarytree;

import java.util.Collection;

public interface BinarySearchTree<T extends Comparable<T>> {

    void insert(T val);

    void insertAll(Collection<T> val);

    boolean contains(T val);

    void delete(T val);
}
