package com.janosgyerik.tools.algorithm.tree.binarytree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private TreeNode<T> root = null;

    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public void insert(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);
        if (root == null) {
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    @Override
    public void insertAll(Collection<T> values) {
        values.forEach(this::insert);
    }

    private void insert(TreeNode<T> node, TreeNode<T> other) {
        assert other != null;

        int compare = other.value.compareTo(node.value);

        if (compare < 0) {
            if (node.left == null) {
                node.left = other;
                other.parent = node;
            } else {
                insert(node.left, other);
            }
        } else if (compare > 0) {
            if (node.right == null) {
                node.right = other;
                other.parent = node;
            } else {
                insert(node.right, other);
            }
        }
    }

    private TreeNode<T> find(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int compare = node.value.compareTo(value);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return find(node.right, value);
        }
        return find(node.left, value);
    }

    @Override
    public boolean contains(T value) {
        return find(root, value) != null;
    }

    @Override
    public void delete(T value) {
        TreeNode<T> deleted = find(root, value);
        if (deleted == null) {
            return;
        }

        if (deleted.equals(root)) {
            if (root.left != null) {
                deleted = root.right;
                root = root.left;
                if (deleted != null) {
                    insert(root, deleted);
                }
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = null;
            }
        } else {
            TreeNode<T> parent = deleted.parent;
            if (parent.left == deleted) {
                deleted = parent.left;
                parent.left = null;
            } else {
                deleted = parent.right;
                parent.right = null;
            }
            if (deleted.left != null) {
                insert(parent, deleted.left);
            }
            if (deleted.right != null) {
                insert(parent, deleted.right);
            }
        }
    }

    @Override
    public String serialize() {
        StringBuilder builder = new StringBuilder();

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(getRoot());

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            if (node == null) {
                builder.append('#');
            } else {
                builder.append(node.value);
                queue.add(node.left);
                queue.add(node.right);
            }
            builder.append(',');
        }

        return builder.toString().replaceAll("[#,]+$", "");
    }
}
