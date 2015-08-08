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
}
