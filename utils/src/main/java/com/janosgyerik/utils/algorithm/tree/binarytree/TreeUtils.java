package com.janosgyerik.utils.algorithm.tree.binarytree;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class TreeUtils {

    private TreeUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    /**
     * "Pretty print" a tree, something like this:
     *                    A
     *          B                  C
     *     D        E         F         G
     *  H    I    J    K    L    M    N    O
     * P Q  R S  T U  V W  X Y  Z 1  2 3  4 5
     *
     * @param root The root node of the tree
     */
    public static void print(TreeNode<?> root, PrintStream out) {
        if (root == null) {
            return;
        }

        int height = height(root);

        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.add(root);

        for (int level = 1; level <= height; ++level) {
            int paddingFirst = (int) Math.pow(2, (double) height - level);
            int paddingBetween = paddingFirst * 2 - 1;

            printPadding(out, paddingFirst);

            List<TreeNode<?>> copy = new LinkedList<>(queue);
            queue.clear();

            for (TreeNode<?> node : copy) {
                if (node == null) {
                    out.print(" ");
                    queue.add(null);
                    queue.add(null);
                } else {
                    out.print(node.value);
                    queue.add(node.left);
                    queue.add(node.right);
                }
                printPadding(out, paddingBetween);
            }
            out.println();
        }
    }

    private static void printPadding(PrintStream out, int padding) {
        for (int i = 0; i < padding; ++i) {
            out.print(" ");
        }
    }

    public static int height(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static int size(TreeNode<?> root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    static <T extends Comparable<T>> boolean isBinarySearchTree(TreeNode<T> root, T minValue, T maxValue) {
        if (root == null) {
            return true;
        }
        return less(minValue, root.value)
            && less(root.value, maxValue)
            && isBinarySearchTree(root.left, minValue, root.value)
            && isBinarySearchTree(root.right, root.value, maxValue);
    }

    private static <T extends Comparable<T>> boolean less(T left, T right) {
        return left.compareTo(right) < 0;
    }
}
