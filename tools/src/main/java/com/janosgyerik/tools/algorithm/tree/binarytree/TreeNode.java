package com.janosgyerik.tools.algorithm.tree.binarytree;

public class TreeNode<T> {

    public final T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
