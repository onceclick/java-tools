package com.janosgyerik.utils.algorithms.tree.binarytree;

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
        assertEquals(1, TreeUtils.height(new Node<>(3)));
    }

    @Test
    public void test_height_leftHigher() {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(4);
        assertEquals(2, TreeUtils.height(root));
    }

    @Test
    public void test_height_rightHigher() {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(4);
        root.right = new Node<>(5);
        root.right.right = new Node<>(6);
        assertEquals(3, TreeUtils.height(root));
    }

    @Test
    public void test_size_empty() {
        assertEquals(0, TreeUtils.size(null));
    }

    @Test
    public void test_size_singleton() {
        assertEquals(1, TreeUtils.size(new Node<>(3)));
    }

    @Test
    public void test_size_leftHigher() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        assertEquals(2, TreeUtils.size(root));
    }

    @Test
    public void test_size_rightHigher() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.right.right = new Node<>(4);
        assertEquals(4, TreeUtils.size(root));
    }

    @Test
    public void test_size_complete() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(4);
        root.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);
        assertEquals(7, TreeUtils.size(root));
    }

    @Test
    public void test_isBinarySearchTree_empty() {
        assertTrue(TreeUtils.isBinarySearchTree(null, 1, 1));
    }

    @Test
    public void test_isBinarySearchTree_singleton() {
        assertTrue(TreeUtils.isBinarySearchTree(new Node<>(3), Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left() {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(2);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_left_right() {
        Node<Integer> root = new Node<>(4);
        root.left = new Node<>(2);
        root.left.right = new Node<>(3);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_right_left() {
        Node<Integer> root = new Node<>(4);
        root.right = new Node<>(6);
        root.right.left = new Node<>(5);
        assertTrue(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_duplicates() {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(3);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_isBinarySearchTree_invalid_left_right() {
        Node<Integer> root = new Node<>(4);
        root.left = new Node<>(2);
        root.left.right = new Node<>(5);
        assertFalse(TreeUtils.isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
