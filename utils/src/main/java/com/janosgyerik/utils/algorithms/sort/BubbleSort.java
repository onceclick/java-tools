package com.janosgyerik.utils.algorithms.sort;

public class BubbleSort {

  private BubbleSort() {
    // utility class, forbidden constructor
  }

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
