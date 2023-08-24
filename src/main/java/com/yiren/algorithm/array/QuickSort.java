package com.yiren.algorithm.array;

import com.yiren.algorithm.utils.AlgoUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * desc  :  快速排序
 * 原理 : 随机挑选一个基准元素 并且偏移左右指针,  遍历数组 , 一次遍历后 ,左边元素比基准元素小 ,右边反之, 递归这个步骤
 * 直到排序完成
 * author: wl
 * date  : 24/6/2023 下午 5:13
 * email : vieper0714@outlook.com
 */
@Slf4j
public class QuickSort implements ArraySortAlgorithmTemplate {

  public static void quick_sort(int[] arr, int left, int right) {
    if (left >= right) return;

    int x = arr[left + right >> 1], i = left - 1, j = right + 1;

    while (i < j) {
      do i++; while (arr[i] < x);
      do j--; while (arr[j] > x);
      if (i < j)
        AlgoUtils.swap(arr, i, j);
    }

    quick_sort(arr, left, j);
    quick_sort(arr, j + 1, right);
  }



  @Override
  public void sort(int[] arr, int left, int right) {
    quick_sort(arr, left, right);
  }


  @Override
  public void execute() {
    int[] test_arr = randomIntArr();
    log.info("排序前的结果 : {}", test_arr);
    quick_sort(test_arr, 0, test_arr.length - 1);
    log.info("快速排序排序后的结果 : {}", test_arr);
  }
}
