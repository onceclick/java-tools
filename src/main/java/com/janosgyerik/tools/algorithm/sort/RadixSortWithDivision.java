package com.janosgyerik.tools.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RadixSortWithDivision {

    public static final int DEFAULT_RADIX = 10;

    public static void sort(int[] arr) {
        sort(arr, DEFAULT_RADIX);
    }

    public static void sort(int[] arr, int radix) {
        int maxDigits = countDigits(getMaxAbs(arr), radix);
        int divisor = 1;
        for (int pos = 0; pos < maxDigits; ++pos) {
            List<List<Integer>> buckets = splitToBuckets(arr, divisor, radix);
            flattenBuckets(arr, buckets);
            divisor *= radix;
        }
        List<List<Integer>> buckets = splitBySign(arr);
        flattenBuckets(arr, buckets);
    }

    private static List<List<Integer>> splitToBuckets(int[] arr, int divisor, int radix) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; ++i) {
            buckets.add(new LinkedList<>());
        }
        for (int num : arr) {
            int bucketIndex = Math.abs(num) / divisor % radix;
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
                negative.add(0, num);
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

    private static int getMaxAbs(int[] arr) {
        int maxAbs = 0;
        for (int num : arr) {
            int absNum = Math.abs(num);
            if (absNum > maxAbs) {
                maxAbs = absNum;
            }
        }
        return maxAbs;
    }

    private static int countDigits(int num, int radix) {
        if (num == 0) {
            return 1;
        }
        int work = num;
        int count = 0;
        while (work > 0) {
            work /= radix;
            ++count;
        }
        return count;
    }
}
