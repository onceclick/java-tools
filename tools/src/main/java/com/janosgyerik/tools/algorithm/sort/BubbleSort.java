package com.janosgyerik.tools.algorithm.sort;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    int work = arr[i];
                    arr[i] = arr[j];
                    arr[j] = work;
                }
            }
        }
    }
}
