package com.janosgyerik.tools.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RadixSortWithBitShifting {

    public static final int DEFAULT_RADIX = 8;

    public static void sort(int[] arr) {
        sort(arr, DEFAULT_RADIX);
    }

    public static void sort(int[] arr, int radix) {
        int shift = 0;
        int radixBits = (int) (Math.pow(2, radix) - 1);
        int positiveCount = 0;
        for (int num : arr) {
            if (num >= 0) {
                ++positiveCount;
            }
        }
        while (true) {
            List<List<Integer>> buckets = splitToBuckets(arr, shift, radixBits);
            if (buckets.get(0).size() == positiveCount) {
                break;
            }
            flattenBuckets(arr, buckets);
            shift += radix;
        }
        List<List<Integer>> buckets = splitBySign(arr);
        flattenBuckets(arr, buckets);
    }

    private static List<List<Integer>> splitToBuckets(int[] arr, int shift, int radixBits) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radixBits + 1; ++i) {
            buckets.add(new LinkedList<>());
        }
        for (int num : arr) {
            int bucketIndex = num;
            if (shift > 0) {
                bucketIndex >>= (shift - 1) >> 1;
            }
            bucketIndex &= radixBits;
            buckets.get(bucketIndex).add(num);
        }
        return buckets;
    }

    private static List<List<Integer>> splitBySign(int[] arr) {
        List<Integer> positive = new LinkedList<>();
        List<Integer> negative = new LinkedList<>();
        for (int num : arr) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }
        return Arrays.asList(negative, positive);
    }

    private static void flattenBuckets(int[] arr, List<? extends List<Integer>> buckets) {
        int i = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[i++] = num;
            }
        }
    }
}
