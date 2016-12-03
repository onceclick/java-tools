package com.janosgyerik.utils.algorithm.sort;

public class InsertionSort {

    private InsertionSort() {
        // utility class, forbidden constructor
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            int value = arr[i];
            int j = i - 1;
            while (0 <= j && value < arr[j]) {
                --j;
            }
            if (j < i - 1) {
                System.arraycopy(arr, j + 1, arr, j + 2, i - j - 1);
                arr[j + 1] = value;
            }
        }
    }
}
