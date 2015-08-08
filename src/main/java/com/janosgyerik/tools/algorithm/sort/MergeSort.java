package com.janosgyerik.tools.algorithm.sort;

public class MergeSort {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    protected static void mergeSort(int[] arr, int from, int to) {
        int diff = to - from;
        if (diff < 2) {
            return;
        }
        int mid = from + diff / 2;
        mergeSort(arr, from, mid);
        mergeSort(arr, mid, to);
        merge(arr, from, mid, to);
    }

    protected static void merge(int[] arr, int from, int mid, int to) {
        int[] sorted = new int[to - from];
        for (int i = 0, pos1 = from, pos2 = mid; i < sorted.length; ++i) {
            if (pos1 < mid && (pos2 >= to || arr[pos1] <= arr[pos2])) {
                sorted[i] = arr[pos1++];
            } else {
                sorted[i] = arr[pos2++];
            }
        }
        System.arraycopy(sorted, 0, arr, from, sorted.length);
    }
}
