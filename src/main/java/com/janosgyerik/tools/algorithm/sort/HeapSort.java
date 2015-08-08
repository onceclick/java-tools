package com.janosgyerik.tools.algorithm.sort;

public class HeapSort {
    public static void sort(int[] arr) {
        heapify(arr, arr.length);
        heapsort(arr);
    }

    private static void heapsort(int[] arr) {
        for (int size = arr.length - 1; size > 0; --size) {
            int highest = arr[0];
            int last = arr[size];
            int i = 0;
            while (true) {
                int leftIndex = i * 2 + 1;
                if (leftIndex <= size) {
                    int rightIndex = i * 2 + 2;
                    if (rightIndex <= size) {
                        if (arr[leftIndex] < arr[rightIndex]) {
                            arr[i] = arr[rightIndex];
                            i = rightIndex;
                        } else {
                            arr[i] = arr[leftIndex];
                            i = leftIndex;
                        }
                    } else {
                        arr[i] = arr[leftIndex];
                        i = leftIndex;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (i != size) {
                arr[i] = last;
                while (true) {
                    int parentIndex = (i - 1) / 2;
                    if (arr[parentIndex] < arr[i]) {
                        swap(arr, parentIndex, i);
                    } else {
                        break;
                    }
                    if (parentIndex == 0) {
                        break;
                    }
                }
            }
            arr[size] = highest;
        }
    }

    protected static void heapify(int[] arr, int size) {
        int currentIndex = (size - 1) / 2;
        while (currentIndex >= 0) {
            siftDown(arr, currentIndex, size - 1);
            --currentIndex;
        }
    }

    private static void siftDown(int[] arr, int start, int size) {
        int index = start;
        while (true) {
            int leftIndex = index * 2 + 1;
            if (leftIndex <= size) {
                int rightIndex = index * 2 + 2;
                if (rightIndex <= size) {
                    if (arr[leftIndex] < arr[rightIndex]) {
                        if (arr[index] < arr[rightIndex]) {
                            swap(arr, index, rightIndex);
                            index = rightIndex;
                        } else if (arr[index] < arr[leftIndex]) {
                            swap(arr, index, leftIndex);
                            index = leftIndex;
                        } else {
                            break;
                        }
                    } else if (arr[index] < arr[leftIndex]) {
                        swap(arr, index, leftIndex);
                        index = leftIndex;
                    } else {
                        break;
                    }
                } else {
                    if (arr[index] < arr[leftIndex]) {
                        swap(arr, index, leftIndex);
                    }
                    break;
                }
            } else {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
