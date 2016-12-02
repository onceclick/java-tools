package com.janosgyerik.utils.algorithm.tree.binarytree;

import java.util.*;

public final class Iterators {

    private Iterators() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public static <T> Iterator<T> preOrderIterator(TreeNode<T> root) {
        return new PreOrderIterator<>(root);
    }

    public static <T> Iterator<T> inOrderIterator(TreeNode<T> root) {
        return new InOrderIterator<>(root);
    }

    public static <T> Iterator<T> postOrderIterator(TreeNode<T> root) {
        return new PostOrderIterator<>(root);
    }

    public static <T> Iterator<T> levelOrderIterator(TreeNode<T> root) {
        return new LevelOrderIterator<>(root);
    }

    private static class PreOrderIterator<T> implements Iterator<T> {
        // Note: Stack is legacy http://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
        private final Deque<TreeNode<T>> stack = new ArrayDeque<>();

        private PreOrderIterator(TreeNode<T> root) {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<T> node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return node.value;
        }
    }

    private static class InOrderIterator<T> implements Iterator<T> {
        private Deque<TreeNode<T>> stack = new ArrayDeque<>();

        private InOrderIterator(TreeNode<T> root) {
            moveToLeftMost(root);
        }

        private void moveToLeftMost(TreeNode<T> start) {
            TreeNode<T> node = start;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<T> current = stack.pop();
            moveToLeftMost(current.right);
            return current.value;
        }
    }

    private static class PostOrderIterator<T> implements Iterator<T> {
        private Deque<TreeNode<T>> stack = new ArrayDeque<>();

        private PostOrderIterator(TreeNode<T> root) {
            moveToNextLeaf(root);
        }

        private void moveToNextLeaf(TreeNode<T> start) {
            TreeNode<T> node = start;
            while (node != null) {
                stack.push(node);
                node = node.left != null ? node.left : node.right;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<T> current = stack.pop();
            if (!stack.isEmpty()) {
                TreeNode<T> parent = stack.peek();
                if (parent.right != current) {
                    moveToNextLeaf(parent.right);
                }
            }
            return current.value;
        }
    }

    private static class LevelOrderIterator<T> implements Iterator<T> {
        private final Queue<TreeNode<T>> queue = new LinkedList<>();

        private LevelOrderIterator(TreeNode<T> root) {
            queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<T> current = queue.poll();

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }

            return current.value;
        }
    }
}
