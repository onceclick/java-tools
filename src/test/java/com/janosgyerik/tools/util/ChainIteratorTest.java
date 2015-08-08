package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.ChainIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ChainIteratorTest {
    @Test
    public void test_single_iterator() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        ChainIterator<Integer> chainIterator = new ChainIterator<>(list.iterator());
        for (Integer item : list) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        assertFalse(chainIterator.hasNext());
    }

    @Test
    public void test_two_iterators() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        ChainIterator<Integer> chainIterator = new ChainIterator<>(
                list1.iterator(),
                list2.iterator()
        );

        for (Integer item : list1) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        for (Integer item : list2) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        assertFalse(chainIterator.hasNext());
    }

    @Test
    public void test_empty_iterator_between_nonempty() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        ChainIterator<Integer> chainIterator = new ChainIterator<>(
                list1.iterator(),
                Collections.<Integer>emptyList().iterator(),
                list2.iterator()
        );

        for (Integer item : list1) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        for (Integer item : list2) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        assertFalse(chainIterator.hasNext());
    }

    @Test
    public void test_with_many_empty_iterators() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        ChainIterator<Integer> chainIterator = new ChainIterator<>(
                Collections.<Integer>emptyList().iterator(),
                list1.iterator(),
                Collections.<Integer>emptyList().iterator(),
                Collections.<Integer>emptyList().iterator(),
                list2.iterator()
        );

        for (Integer item : list1) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        for (Integer item : list2) {
            assertTrue(chainIterator.hasNext());
            assertEquals(item, chainIterator.next());
        }
        assertFalse(chainIterator.hasNext());
    }

    @Test
    public void test_only_empty_iterators() {
        ChainIterator<Integer> chainIterator = new ChainIterator<>(
                Collections.<Integer>emptyList().iterator(),
                Collections.<Integer>emptyList().iterator(),
                Collections.<Integer>emptyList().iterator()
        );
        assertFalse(chainIterator.hasNext());
    }
}
