package com.janosgyerik.utils.algorithms.tree.binarytree;

import java.util.*;

public final class Iterators {

    private Iterators() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public static <T> Iterator<T> preOrderIterator(Node<T> root) {
        return new PreOrderIterator<>(root);
    }

    public static <T> Iterator<T> inOrderIterator(Node<T> root) {
        return new InOrderIterator<>(root);
    }

    public static <T> Iterator<T> postOrderIterator(Node<T> root) {
        return new PostOrderIterator<>(root);
    }

    public static <T> Iterator<T> levelOrderIterator(Node<T> root) {
        return new LevelOrderIterator<>(root);
    }

    private static class PreOrderIterator<T> implements Iterator<T> {
        // Note: Stack is legacy http://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
        private final Deque<Node<T>> stack = new ArrayDeque<>();

        private PreOrderIterator(Node<T> root) {
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
            Node<T> node = stack.pop();
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
        private Deque<Node<T>> stack = new ArrayDeque<>();

        private InOrderIterator(Node<T> root) {
            moveToLeftMost(root);
        }

        private void moveToLeftMost(Node<T> start) {
            Node<T> node = start;
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
            Node<T> current = stack.pop();
            moveToLeftMost(current.right);
            return current.value;
        }
    }

    private static class PostOrderIterator<T> implements Iterator<T> {
        private Deque<Node<T>> stack = new ArrayDeque<>();

        private PostOrderIterator(Node<T> root) {
            moveToNextLeaf(root);
        }

        private void moveToNextLeaf(Node<T> start) {
            Node<T> node = start;
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
            Node<T> current = stack.pop();
            if (!stack.isEmpty()) {
                Node<T> parent = stack.peek();
                if (parent.right != current) {
                    moveToNextLeaf(parent.right);
                }
            }
            return current.value;
        }
    }

    private static class LevelOrderIterator<T> implements Iterator<T> {
        private final Queue<Node<T>> queue = new LinkedList<>();

        private LevelOrderIterator(Node<T> root) {
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
            Node<T> current = queue.poll();

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
