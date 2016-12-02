package com.janosgyerik.utils.algorithm.tree.binarytree;

public class TreeNode<T> {

    public final T value;
    public TreeNode<T> parent;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this(null, value);
    }

    public TreeNode(TreeNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
