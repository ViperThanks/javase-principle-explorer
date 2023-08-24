package com.yiren.algorithm.array;

import com.yiren.algorithm.utils.AlgoUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * desc  : 堆排 先建堆再排序
 * author: wl
 * date  : 24/6/2023 下午 11:32
 * email : vieper0714@outlook.com
 */
@Slf4j
public class HeapSort implements ArraySortAlgorithmTemplate{
  public static void heap_sort(int[] arr) {

    int n = arr.length;
    for (int i = n / 2 - 1; i >= 0; i--)
      down(arr, i, n);
    for (int i = n - 1; i > 0; i--) {
      AlgoUtils.swap(arr, 0, i);
      down(arr, 0, i);
    }
  }

  private static void down(int[] arr, int i, int n) {
    int j = i * 2 + 1;
    while (j < n) {
      if (j + 1 < n && arr[j + 1] > arr[j])
        j++;
      if (arr[i] >= arr[j])
        break;
      AlgoUtils.swap(arr, i, j);
      i = j;
      j = i * 2 + 1;
    }
  }


  @Override
  public void sort(int[] arr, int left, int right) {
    heap_sort(arr);
  }

  @Override
  public void execute() {
    int[] test_arr = randomIntArr();
    log.info("排序前的结果 : {}", Arrays.toString(test_arr));
    heap_sort(test_arr);
    log.info("堆排序排序后的结果 : {}", test_arr);
  }
}
