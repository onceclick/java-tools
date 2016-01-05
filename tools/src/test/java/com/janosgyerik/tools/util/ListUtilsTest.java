package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.ListUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtilsTest {
    private <T> List<List<T>> partition(List<T> items, int size) {
        return ListUtils.partition(items, size);
    }

    @Test
    public void testPartitionEmpty() {
        assertEquals(Collections.EMPTY_LIST, partition(Arrays.asList(), 3));
    }

    @Test(expected = NullPointerException.class)
    public void testPartitionNull() {
        partition(null, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartitionZeroSize() {
        partition(Arrays.asList(1, 2, 3), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPartitionNegativeSize() {
        partition(Arrays.asList(1, 2, 3), -3);
    }

    @Test
    public void testPartitionInts() {
        assertEquals(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5)), partition(Arrays.asList(1, 2, 3, 4, 5), 2));
    }

    @Test
    public void testPartitionStrings() {
        assertEquals(Arrays.asList(Arrays.asList("1", "2"), Arrays.asList("3", "4"), Arrays.asList("5")), partition(Arrays.asList("1", "2", "3", "4", "5"), 2));
    }

    @Test
    public void testPartition_MultipleOfSize() {
        assertEquals(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), partition(Arrays.asList(1, 2, 3, 4), 2));
    }

    @Test
    public void testPartition_Size1() {
        assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4)), partition(Arrays.asList(1, 2, 3, 4), 1));
    }

    @Test
    public void testPartition_BiggerSize() {
        List<Integer> orig = Arrays.asList(1, 2, 3, 4);
        assertEquals(Arrays.asList(orig), partition(orig, orig.size() + 11));
    }

}
