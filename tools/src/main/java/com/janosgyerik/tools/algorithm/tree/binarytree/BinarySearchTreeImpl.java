package com.janosgyerik.tools.algorithm.tree.binarytree;

import java.util.Collection;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private TreeNode<T> root = null;

    public TreeNode<T> getRoot() {
        return root;
    }

    @Override
    public void insert(T val) {
        TreeNode<T> newNode = new TreeNode<>(val);
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

        if (other.value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = other;
                other.parent = node;
            } else {
                insert(node.left, other);
            }
        } else if (node.value.compareTo(other.value) < 0) {
            if (node.right == null) {
                node.right = other;
                other.parent = node;
            } else {
                insert(node.right, other);
            }
        }
    }

    private TreeNode<T> find(TreeNode<T> node, T val) {
        if (node == null) {
            return null;
        }
        int compare = node.value.compareTo(val);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return find(node.right, val);
        }
        return find(node.left, val);
    }

    @Override
    public boolean contains(T val) {
        return find(root, val) != null;
    }

    @Override
    public void delete(T val) {
        TreeNode<T> deleted = find(root, val);
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
}
