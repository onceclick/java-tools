package com.janosgyerik.tools.algorithm.tree.binarytree;

import java.util.Collection;
import java.util.Iterator;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private TreeNode<T> root = null;

    @Override
    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public void insert(T val) {
        if (root == null) {
            root = new TreeNode<>(val);
        } else {
            insert(root, val);
        }
    }

    @Override
    public void insertAll(Collection<T> values) {
        values.forEach(this::insert);
    }

    private void insert(TreeNode<T> node, T val) {
        if (val.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new TreeNode<>(val);
            } else {
                insert(node.left, val);
            }
        } else if (node.value.compareTo(val) < 0) {
            if (node.right == null) {
                node.right = new TreeNode<>(val);
            } else {
                insert(node.right, val);
            }
        }
    }

    @Override
    public boolean contains(T val) {
        return contains(root, val);
    }

    private boolean contains(TreeNode<T> node, T val) {
        if (node == null) {
            return false;
        }
        int compare = val.compareTo(node.value);
        if (compare == 0) {
            return true;
        }
        if (compare < 0) {
            return contains(node.left, val);
        }
        return contains(node.right, val);
    }

    @Override
    public void delete(T val) {
        TreeNode<T> deleted = deleteSubTree(val);
        if (deleted == null) {
            return;
        }
        if (deleted.equals(root)) {
            deleted = new TreeNode<>(null);
            deleted.left = root.left;
            root = root.right;
        }
        Iterator<T> iterator = Iterators.levelOrderIterator(deleted);
        iterator.next();
        while (iterator.hasNext()) {
            insert(iterator.next());
        }
    }

    TreeNode<T> deleteSubTree(T val) {
        if (root == null || root.value.equals(val)) {
            return root;
        }
        TreeNode<T> deleted = null;
        TreeNode<T> node = root;
        while (node != null) {
            if (node.value.compareTo(val) > 0) {
                if (node.left.value.equals(val)) {
                    deleted = node.left;
                    node.left = null;
                }
                node = node.left;
            } else {
                if (node.right.value.equals(val)) {
                    deleted = node.right;
                    node.right = null;
                }
                node = node.right;
            }
        }
        return deleted;
    }
}
