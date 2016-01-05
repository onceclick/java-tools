package com.janosgyerik.tools.algorithm.sort;

import com.janosgyerik.tools.util.IterTools;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class SortTest {

    private static final int MAX_LENGTH_TO_COMPARE_WITH_TOSTRING = 15;

    abstract void sort(int[] arr);

    private int[] newRandomArray(int num) {
        Random random = new Random(0);
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private void sortAndVerify(int[] arr) {
        int[] orig = arr.clone();
        int[] copy = arr.clone();
        Arrays.sort(copy);
        sort(arr);

        if (arr.length < MAX_LENGTH_TO_COMPARE_WITH_TOSTRING) {
            assertEquals("should sort correctly: " + Arrays.toString(orig),
                    Arrays.toString(copy), Arrays.toString(arr));
        } else {
            assertArrayEquals(copy, arr);
        }
    }

    @Test
    public void test_empty() {
        sortAndVerify(new int[0]);
    }

    @Test
    public void test_1() {
        sortAndVerify(new int[]{1});
    }

    @Test
    public void test_1_2() {
        sortAndVerify(new int[]{1, 2});
    }

    @Test
    public void test_2_1() {
        sortAndVerify(new int[]{2, 1});
    }

    @Test
    public void test_1_2_3() {
        sortAndVerify(new int[]{1, 2, 3});
    }

    @Test
    public void test_1_2_3_4_5_6_7_8_9() {
        sortAndVerify(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    @Test
    public void test_3_2_1() {
        sortAndVerify(new int[]{3, 2, 1});
    }

    @Test
    public void test_m2_m3_m1() {
        sortAndVerify(new int[]{-2, -3, -1});
    }

    @Test
    public void test_m300_m200_m999() {
        sortAndVerify(new int[]{-30, -200, -999});
    }

    @Test
    public void test_m300_m200_m1000() {
        sortAndVerify(new int[]{-300, -200, -1000});
    }

    @Test
    public void test_m300_m200_0() {
        sortAndVerify(new int[]{-300, -200, 0});
    }

    @Test
    public void test_0() {
        sortAndVerify(new int[]{0});
    }

    @Test
    public void test_random_10() {
        sortAndVerify(newRandomArray(10));
    }

    @Test
    public void test_random_1000() {
        sortAndVerify(newRandomArray(1000));
    }

    @Test
    public void test_permutations_1_2_3_4_5_6_7_8() {
        Set<List<Integer>> permutations = IterTools.permutations(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        for (List<Integer> combo : permutations) {
            sortAndVerify(toArrary(combo));
        }
    }

    private int[] toArrary(List<Integer> combo) {
        int[] arr = new int[combo.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = combo.get(i);
        }
        return arr;
    }

}

