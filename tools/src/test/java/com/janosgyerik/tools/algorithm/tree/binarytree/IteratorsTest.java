package com.janosgyerik.tools.algorithm.tree.binarytree;

import org.junit.Test;

import java.util.*;

import static com.janosgyerik.tools.algorithm.tree.binarytree.Iterators.*;
import static org.junit.Assert.*;

public class IteratorsTest {

    // example tree from http://en.wikipedia.org/wiki/Tree_traversal
    /*
            F
        B       G
      A   D       I
         C E     H
          */
    private final TreeNode<Character> root;

    public IteratorsTest() {
        root = new TreeNode<>('F');
        root.left = new TreeNode<>('B');
        root.left.left = new TreeNode<>('A');
        root.left.right = new TreeNode<>('D');
        root.left.right.left = new TreeNode<>('C');
        root.left.right.right = new TreeNode<>('E');
        root.right = new TreeNode<>('G');
        root.right.right = new TreeNode<>('I');
        root.right.right.left = new TreeNode<>('H');
    }

    static <T> List<T> iterateToList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Test
    public void test_preOrderIterator() {
        Iterator<Character> iterator = preOrderIterator(root);
        assertEquals(Arrays.asList('F', 'B', 'A', 'D', 'C', 'E', 'G', 'I', 'H'), iterateToList(iterator));
    }

    @Test(expected = NoSuchElementException.class)
    public void preOrderIterator_should_throw_if_iterated_beyond() {
        Iterator<Character> iterator = preOrderIterator(new TreeNode<>('A'));
        iterator.next();
        iterator.next();
    }

    @Test
    public void test_inOrderIterator() {
        Iterator<Character> iterator = inOrderIterator(root);
        assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'), iterateToList(iterator));
    }

    @Test(expected = NoSuchElementException.class)
    public void inOrderIterator_should_throw_if_iterated_beyond() {
        Iterator<Character> iterator = inOrderIterator(new TreeNode<>('A'));
        iterator.next();
        iterator.next();
    }

    @Test
    public void test_postOrderIterator() {
        Iterator<Character> iterator = postOrderIterator(root);
        assertEquals(Arrays.asList('A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F'), iterateToList(iterator));
    }

    @Test(expected = NoSuchElementException.class)
    public void postOrderIterator_should_throw_if_iterated_beyond() {
        Iterator<Character> iterator = postOrderIterator(new TreeNode<>('A'));
        iterator.next();
        iterator.next();
    }

    @Test
    public void test_levelOrderIterator() {
        Iterator<Character> iterator = levelOrderIterator(root);
        assertEquals(Arrays.asList('F', 'B', 'G', 'A', 'D', 'I', 'C', 'E', 'H'), iterateToList(iterator));
    }

    @Test(expected = NoSuchElementException.class)
    public void levelOrderIterator_should_throw_if_iterated_beyond() {
        Iterator<Character> iterator = levelOrderIterator(new TreeNode<>('A'));
        iterator.next();
        iterator.next();
    }
}
