package com.janosgyerik.utils.algorithm.tree.binarytree;

class Node<T> {

    public final T value;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T value) {
        this(null, value);
    }

    public Node(Node<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
