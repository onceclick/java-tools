package com.janosgyerik.utils.algorithm.tree.binarytree.balanced;

import com.janosgyerik.utils.algorithm.tree.binarytree.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class BalancedTreeUtilsTest {
    @Test
    public void test_empty_is_balanced() {
        assertTrue(BalancedTreeUtils.isBalanced(null));
    }

    @Test
    public void test_singleton_is_balanced() {
        assertTrue(BalancedTreeUtils.isBalanced(new Node<>(3)));
    }

    @Test
    public void test_isBalanced_unbalanced_left() {
        Node<Integer> root = new Node<>(3);

        root.left = new Node<>(4);
        assertTrue(BalancedTreeUtils.isBalanced(root));

        root.left.left = new Node<>(5);
        assertFalse(BalancedTreeUtils.isBalanced(root));
    }

    @Test
    public void test_isBalanced_unbalanced_right() {
        Node<Integer> root = new Node<>(3);

        root.left = new Node<>(4);
        root.left.left = new Node<>(5);
        assertFalse(BalancedTreeUtils.isBalanced(root));

        root.right = new Node<>(6);
        assertTrue(BalancedTreeUtils.isBalanced(root));
        root.right.right = new Node<>(7);
        assertTrue(BalancedTreeUtils.isBalanced(root));

        root.right.right.left = new Node<>(8);
        assertFalse(BalancedTreeUtils.isBalanced(root));
    }
}
