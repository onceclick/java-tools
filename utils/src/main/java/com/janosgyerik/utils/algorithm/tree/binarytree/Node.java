package com.janosgyerik.utils.algorithm.tree.binarytree;

class Node<T> {

    public final T value;
    Node<T> parent;
    Node<T> left;
    Node<T> right;

    Node(T value) {
        this(null, value);
    }

    private Node(Node<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
