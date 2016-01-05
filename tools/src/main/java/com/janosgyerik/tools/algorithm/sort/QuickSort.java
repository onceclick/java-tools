package com.janosgyerik.tools.algorithm.sort;

public class QuickSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int mid = partition(arr, start, end);
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int midIndex = start + (end - start) / 2;
        int midValue = arr[midIndex];

        int midFirst = midIndex;
        while (midFirst > start && arr[midFirst - 1] == midValue) {
            --midFirst;
        }

        int midLast = midIndex;
        while (midLast < end - 1 && arr[midLast + 1] == midValue) {
            ++midLast;
        }

        int higherIndexInLeft = findHigherIndexInLeft(arr, start, midIndex, midValue);
        int lowerIndexInRight = findLowerIndexInRight(arr, midIndex, end, midValue);
        while (true) {
            if (higherIndexInLeft != -1 && lowerIndexInRight != -1) {
                swap(arr, higherIndexInLeft, lowerIndexInRight);
                higherIndexInLeft = findHigherIndexInLeft(arr, start, higherIndexInLeft, midValue);
                lowerIndexInRight = findLowerIndexInRight(arr, lowerIndexInRight, end, midValue);
            } else if (higherIndexInLeft != -1) {
                int tmp = arr[higherIndexInLeft];
                System.arraycopy(arr, higherIndexInLeft + 1, arr, higherIndexInLeft, midLast - higherIndexInLeft);
                arr[midLast] = tmp;
                --midIndex;
                --midLast;
                --midFirst;
                higherIndexInLeft = findHigherIndexInLeft(arr, start, higherIndexInLeft, midValue);
            } else if (lowerIndexInRight != -1) {
                int tmp = arr[lowerIndexInRight];
                System.arraycopy(arr, midFirst, arr, midFirst + 1, lowerIndexInRight - midFirst);
                arr[midFirst] = tmp;
                ++midIndex;
                ++midLast;
                ++midFirst;
                lowerIndexInRight = findLowerIndexInRight(arr, lowerIndexInRight, end, midValue);
            } else {
                break;
            }
        }
        return midIndex;
    }

    private static int findHigherIndexInLeft(int[] arr, int start, int from, int midValue) {
        for (int i = from - 1; i >= start; --i) {
            if (arr[i] > midValue) {
                return i;
            }
        }
        return -1;
    }

    private static int findLowerIndexInRight(int[] arr, int from, int end, int midValue) {
        for (int i = from + 1; i < end; ++i) {
            if (arr[i] < midValue) {
                return i;
            }
        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int work = arr[i];
        arr[i] = arr[j];
        arr[j] = work;
    }
}
