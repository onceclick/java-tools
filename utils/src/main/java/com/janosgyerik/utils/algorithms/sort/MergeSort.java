package com.janosgyerik.utils.algorithms.sort;

public class MergeSort {

    private MergeSort() {
        // utility class, forbidden constructor
    }

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

    static void merge(int[] arr, int from, int mid, int to) {
        int[] sorted = new int[to - from];
        int i = 0;
        int pos1 = from;
        int pos2 = mid;

        while (pos1 < mid && pos2 < to) {
            if (arr[pos1] <= arr[pos2]) {
                sorted[i++] = arr[pos1++];
            } else {
                sorted[i++] = arr[pos2++];
            }
        }
        while (pos1 < mid) {
            sorted[i++] = arr[pos1++];
        }
        while (pos2 < to) {
            sorted[i++] = arr[pos2++];
        }
        System.arraycopy(sorted, 0, arr, from, sorted.length);
    }
}
