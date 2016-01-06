package com.janosgyerik.tools.algorithm.tree.binarytree;

import com.janosgyerik.tools.util.ListUtils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public abstract class BinarySearchTreeTest {

    protected abstract <T extends Comparable<T>> BinarySearchTree<T> newBinarySearchTree();

    @Test
    public void test_insert_contains() {
        BinarySearchTree<Integer> tree = newBinarySearchTree();
        int val = 3;
        assertFalse(tree.contains(val));
        tree.insert(val);
        assertTrue(tree.contains(val));
        assertFalse(tree.contains(val + 1));
    }

    private List<Integer> createList(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; ++i) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void test_bst_from_sorted_list() {
        int num = 10;
        List<Integer> list = createList(num);

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), 0, num + 1));
    }

    @Test
    public void test_bst_from_shuffled_list() {
        int num = 10;
        List<Integer> list = createList(num);
        Collections.shuffle(list, new Random(1));

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), 0, num + 1));
    }

    @Test
    public void test_sorted_inorder() {
        int num = 10;
        List<Integer> list = createList(num);
        List<Integer> shuffled = new ArrayList<>(list);
        Collections.shuffle(shuffled, new Random(1));
        assertNotEquals(list, shuffled);

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertEquals(list, ListUtils.toList(Iterators.inOrderIterator(tree.getRoot())));
    }

    private void assertBinarySearchTree(BinarySearchTree<Integer> tree) {
        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    private void assertTreeSize(int size, BinarySearchTree<Integer> tree) {
        assertEquals(size, TreeUtils.size(tree.getRoot()));
    }

    private void testDeleteNode(List<Integer> list, int value) {
        BinarySearchTree<Integer> tree = newBinarySearchTree();
        list.forEach(tree::insert);

        assertTreeSize(list.size(), tree);
        assertBinarySearchTree(tree);

        tree.delete(value);
        assertTreeSize(list.size() - 1, tree);
        assertBinarySearchTree(tree);
    }

    @Test
    public void test_delete_leaf() {
        testDeleteNode(Arrays.asList(1, 2, 3), 3);
    }

    @Test
    public void test_delete_nonleaf() {
        testDeleteNode(Arrays.asList(1, 2, 3), 2);
    }

    @Test
    public void test_delete_root_with_only_left() {
        testDeleteNode(Arrays.asList(3, 2, 1), 3);
    }

    @Test
    public void test_delete_root_with_only_right() {
        testDeleteNode(Arrays.asList(1, 2, 3), 1);
    }

    @Test
    public void test_delete_root_with_both_left_right() {
        testDeleteNode(Arrays.asList(2, 1, 3), 2);
    }

    @Test
    public void test_delete_various() {
        List<Integer> list = createList(10);
        Collections.shuffle(list);

        BinarySearchTree<Integer> tree = newBinarySearchTree();
        tree.insertAll(list);

        int min = -1;
        int max = list.size() + 1;
        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), min, max));
        assertEquals(list.size(), TreeUtils.size(tree.getRoot()));

        tree.delete(list.get(0));
        assertTrue(TreeUtils.isBinarySearchTree(tree.getRoot(), min, max));
        assertEquals(list.size() - 1, TreeUtils.size(tree.getRoot()));
    }
}
