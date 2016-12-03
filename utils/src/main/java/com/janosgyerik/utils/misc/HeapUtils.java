package com.janosgyerik.utils.misc;

public final class HeapUtils {

    private HeapUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public static boolean isHeap(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int leftIndex = i * 2 + 1;
            if (leftIndex < arr.length) {
                if (arr[i] < arr[leftIndex]) {
                    return false;
                }
                int rightIndex = i * 2 + 2;
                if (rightIndex < arr.length) {
                    if (arr[i] < arr[rightIndex]) {
                        return false;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return true;
    }

    public static void heapify(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int leftIndex = getLeftIndex(i);
            if (leftIndex < arr.length) {
                siftUp(arr, leftIndex);
                int rightIndex = getRightIndex(i);
                if (rightIndex < arr.length) {
                    siftUp(arr, rightIndex);
                }
            }
        }
    }

    private static void siftUp(int[] arr, int start) {
        int index = start;
        do {
            int parentIndex = getParentIndex(index);
            if (arr[parentIndex] >= arr[index]) {
                break;
            }
            swap(arr, parentIndex, index);
            index = parentIndex;
        } while (index != 0);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    private static int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    private static int getRightIndex(int index) {
        return 2 * index + 2;
    }

    private static int getParentIndex(int index) {
        return (index - 1) / 2;
    }
}
