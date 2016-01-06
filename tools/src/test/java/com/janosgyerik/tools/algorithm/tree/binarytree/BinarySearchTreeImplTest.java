package com.janosgyerik.tools.algorithm.tree.binarytree;

public class BinarySearchTreeImplTest extends BinarySearchTreeTest {
    @Override
    protected <T extends Comparable<T>> BinarySearchTree<T> newBinarySearchTree() {
        return new BinarySearchTreeImpl<>();
    }
}
