package com.janosgyerik.tools.algorithm.tree.binarytree.balanced;

import com.janosgyerik.tools.algorithm.tree.binarytree.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BalancedTreeUtilsTest {
    @Test
    public void testBalanced_empty() {
        assertTrue(BalancedTreeUtils.isBalanced(null));
    }

    @Test
    public void testBalanced_singleton() {
        assertTrue(BalancedTreeUtils.isBalanced(new TreeNode<>(3)));
    }

    @Test
    public void testBalanced_unbalanced_left() {
        TreeNode<Integer> root = new TreeNode<>(3);

        root.left = new TreeNode<>(4);
        assertTrue(BalancedTreeUtils.isBalanced(root));

        root.left.left = new TreeNode<>(5);
        assertFalse(BalancedTreeUtils.isBalanced(root));
    }

    @Test
    public void testBalanced_unbalanced_right() {
        TreeNode<Integer> root = new TreeNode<>(3);

        root.left = new TreeNode<>(4);
        root.left.left = new TreeNode<>(5);
        assertFalse(BalancedTreeUtils.isBalanced(root));

        root.right = new TreeNode<>(6);
        assertTrue(BalancedTreeUtils.isBalanced(root));
        root.right.right = new TreeNode<>(7);
        assertTrue(BalancedTreeUtils.isBalanced(root));

        root.right.right.left = new TreeNode<>(8);
        assertFalse(BalancedTreeUtils.isBalanced(root));
    }
}
