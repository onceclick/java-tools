package com.janosgyerik.tools.algorithm.sort;

import org.junit.Ignore;

@Ignore
public class RadixSortWithBitShiftingTest extends SortTest {
    @Override
    void sort(int[] arr) {
        RadixSortWithBitShifting.sort(arr);
    }
}