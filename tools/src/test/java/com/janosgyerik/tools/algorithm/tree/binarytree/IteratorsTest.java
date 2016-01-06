package com.janosgyerik.tools.algorithm.tree.binarytree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public void testPreOrderIterator() {
        Iterator<Character> iterator = Iterators.preOrderIterator(root);
        assertEquals(Arrays.asList('F', 'B', 'A', 'D', 'C', 'E', 'G', 'I', 'H'), iterateToList(iterator));
    }

    @Test
    public void testInOrderIterator() {
        Iterator<Character> iterator = Iterators.inOrderIterator(root);
        assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'), iterateToList(iterator));
    }

    @Test
    public void testPostOrderIterator() {
        Iterator<Character> iterator = Iterators.postOrderIterator(root);
        assertEquals(Arrays.asList('A', 'C', 'E', 'D', 'B', 'H', 'I', 'G', 'F'), iterateToList(iterator));
    }

    @Test
    public void testLevelOrderIterator() {
        Iterator<Character> iterator = Iterators.levelOrderIterator(root);
        assertEquals(Arrays.asList('F', 'B', 'G', 'A', 'D', 'I', 'C', 'E', 'H'), iterateToList(iterator));
    }
}
