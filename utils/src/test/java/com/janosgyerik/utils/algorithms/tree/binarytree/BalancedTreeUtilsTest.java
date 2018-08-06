package com.janosgyerik.utils.algorithms.tree.binarytree;

import org.junit.Test;

import static com.janosgyerik.utils.algorithms.tree.binarytree.TreeUtils.isBalanced;
import static org.junit.Assert.*;

public class BalancedTreeUtilsTest {
    @Test
    public void test_empty_is_balanced() {
        assertTrue(isBalanced(null));
    }

    @Test
    public void test_singleton_is_balanced() {
        assertTrue(isBalanced(new Node<>(3)));
    }

    @Test
    public void test_isBalanced_unbalanced_left() {
        Node<Integer> root = new Node<>(3);

        root.left = new Node<>(4);
        assertTrue(isBalanced(root));

        root.left.left = new Node<>(5);
        assertFalse(isBalanced(root));
    }

    @Test
    public void test_isBalanced_unbalanced_right() {
        Node<Integer> root = new Node<>(3);

        root.left = new Node<>(4);
        root.left.left = new Node<>(5);
        assertFalse(isBalanced(root));

        root.right = new Node<>(6);
        assertTrue(isBalanced(root));
        root.right.right = new Node<>(7);
        assertTrue(isBalanced(root));

        root.right.right.left = new Node<>(8);
        assertFalse(isBalanced(root));
    }
}
