package com.yiren.algorithm.array;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc  : 归并排序 先递归再排序
 * author: wl
 * date  : 24/6/2023 下午 5:54
 * email : vieper0714@outlook.com
 */

public class MergeSort implements ArraySortAlgorithmTemplate{

  private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

  public static void merge_sort(int[] arr, int l, int r) {
    if (l >= r) return;
    int mid = l + r >> 1;

    merge_sort(arr, l, mid);
    merge_sort(arr, mid + 1, r);

    int[] tmp = new int[r - l + 1];
    int k = 0, i = l, j = mid + 1;

    while (i <= mid && j <= r) {
      if (arr[i] <= arr[j])
        tmp[k++] = arr[i++];
      else
        tmp[k++] = arr[j++];
    }
    while (i <= mid)
      tmp[k++] = arr[i++];
    while (j <= r)
      tmp[k++] = arr[j++];
    for (i = l, j = 0; i <= r; )
      arr[i++] = tmp[j++];
  }

  @Override
  public void sort(int[] arr, int left, int right) {
    merge_sort(arr,left,right);
  }


  @Override
  public void execute() {
    int[] test_arr = randomIntArr();
    log.info("排序前的结果 : {}", test_arr);
    merge_sort(test_arr,0,test_arr.length-1);
    log.info("归并排序排序后的结果 : {}", test_arr);
  }
}
