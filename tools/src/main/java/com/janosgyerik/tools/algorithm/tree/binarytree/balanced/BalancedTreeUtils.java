package com.janosgyerik.tools.algorithm.tree.binarytree.balanced;

import com.janosgyerik.tools.algorithm.tree.binarytree.TreeNode;

public class BalancedTreeUtils {

    private static final int UNBALANCED = -1;

    private BalancedTreeUtils() {
        // utility class, forbidden constructor
    }

    /**
     * Check if a binary tree is height-balanced:
     *
     * 1. The heights of the left and right sub-trees differ by at most 1
     * 2. The left sub-tree is balanced
     * 3. The rightsub-tree is balanced
     *
     * @param root the root node of the tree
     * @return true if the tree is balanced
     */
    public static boolean isBalanced(TreeNode<?> root) {
        return balancedHeight(root) != UNBALANCED;
    }

    /**
     * Find the height of a balanced binary tree.
     * If the tree is not balanced, return the constant UNBALANCED.
     *
     * @param root the root node of the tree
     * @return the height if the tree is balanced, otherwise the constant UNBALANCED
     */
    private static int balancedHeight(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = balancedHeight(root.left);
        if (leftHeight == UNBALANCED) {
            return UNBALANCED;
        }

        int rightHeight = balancedHeight(root.right);
        if (rightHeight == UNBALANCED) {
            return UNBALANCED;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return UNBALANCED;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
