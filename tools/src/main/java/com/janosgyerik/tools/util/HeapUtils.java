package com.janosgyerik.tools.util;

public class HeapUtils {

    private HeapUtils() {
        // utility class, forbidden constructor
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
                int left = arr[leftIndex];
                if (arr[i] < left) {
                    swap(arr, i, leftIndex);
                    siftUp(arr, i);
                }
            }

            int rightIndex = getRightIndex(i);
            if (rightIndex < arr.length) {
                int right = arr[rightIndex];
                if (arr[i] < right) {
                    swap(arr, i, rightIndex);
                    siftUp(arr, i);
                }
            }
        }
    }

    private static void siftUp(int[] arr, int index) {
        for (int i = index; i > 0; ) {
            int parentIndex = getParentIndex(i);
            int parent = arr[parentIndex];
            int item = arr[i];
            if (parent >= item) {
                break;
            }
            swap(arr, parentIndex, i);
            i = parentIndex;
        }
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
