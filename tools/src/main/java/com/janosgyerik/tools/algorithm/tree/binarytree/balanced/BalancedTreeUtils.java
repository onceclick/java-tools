package com.janosgyerik.tools.algorithm.tree.binarytree.balanced;

import com.janosgyerik.tools.algorithm.tree.binarytree.TreeNode;

public class BalancedTreeUtils {

    private static final int UNBALANCED = -1;

    private BalancedTreeUtils() {
        // utility class, forbidden constructor
    }

    public static boolean isBalanced(TreeNode<?> root) {
        return getHeightIfBalanced(root) != UNBALANCED;
    }

    private static int getHeightIfBalanced(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightIfBalanced(root.left);
        if (leftHeight == UNBALANCED) {
            return UNBALANCED;
        }

        int rightHeight = getHeightIfBalanced(root.right);
        if (rightHeight == UNBALANCED) {
            return UNBALANCED;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return UNBALANCED;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
