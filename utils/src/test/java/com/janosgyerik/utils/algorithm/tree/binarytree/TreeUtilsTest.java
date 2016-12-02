package com.janosgyerik.utils.algorithm.tree.binarytree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeUtilsTest {

    @Test
    public void test_height_empty() {
        assertEquals(0, TreeUtils.height(null));
    }

    @Test
    public void test_height_singleton() {
        assertEquals(1, TreeUtils.height(new TreeNode<>(3)));
    }

    @Test
    public void test_height_leftHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        assertEquals(2, TreeUtils.height(root));
    }

    @Test
    public void test_height_rightHigher() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        assertEquals(3, TreeUtils.height(root));
    }

    @Test
    public void test_size_empty() {
        assertEquals(0, TreeUtils.size(null));
    }

    @Test
    public void test_size_singleton() {
        assertEquals(1, TreeUtils.size(new TreeNode<>(3)));
    }

    @Test
    public void test_size_leftHigher() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        assertEquals(2, TreeUtils.size(root));
    }

    @Test
    public void test_size_rightHigher() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(4);
        assertEquals(4, TreeUtils.size(root));
    }

    @Test
    public void test_size_complete() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        assertEquals(7, TreeUtils.size(root));
    }

    @Test
    public void test_isBinarySearchTree_empty() {
        assertTrue(TreeUtils.isBinarySearchTree(null, 1, 1));
    }

    @Test
    public void test_isBinarySearchTree_singleton() {
        assertTrue(TreeUtils.isBinarySearchTree(new TreeNode<>(3), Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(2);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left_right() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(3);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_right_left() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.right = new TreeNode<>(6);
        root.right.left = new TreeNode<>(5);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_duplicates() {
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(3);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_left_right() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(5);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
