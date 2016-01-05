package com.janosgyerik.tools.algorithm.sort;

import static com.janosgyerik.tools.util.HeapUtils.heapify;

public class HeapSort {
    public static void sort(int[] arr) {
        heapify(arr);
        heapsort(arr);
    }

    private static void heapsort(int[] arr) {
        for (int i = arr.length - 1; i > 0; --i) {
            swap(arr, 0, i);
            siftDown(arr, 0, i);
        }
    }

    private static void siftDown(int[] arr, int i, int length) {
        int leftIndex = i * 2 + 1;
        if (leftIndex < length && arr[i] < arr[leftIndex]) {
            swap(arr, i, leftIndex);
            siftDown(arr, leftIndex, length);
        }
        int rightIndex = i * 2 + 2;
        if (rightIndex < length && arr[i] < arr[rightIndex]) {
            swap(arr, i, rightIndex);
            siftDown(arr, rightIndex, length);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
