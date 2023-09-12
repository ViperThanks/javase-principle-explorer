package com.yiren.algorithm.array;

import com.yiren.algorithm.AlgorithmTemplate;
import com.yiren.algorithm.utils.AlgoUtils;

/**
 * desc  : 数组排序算法模板
 * 提供核心方法{@linkplain ArraySortAlgorithmTemplate#sort(int[], int, int)}
 * 有归并排序 merge sort {@link MergeSort}
 * 有快速排序 quick sort {@link QuickSort}
 * 有堆排序   heap sort {@link HeapSort}
 * author: wl
 * date  : 24/6/2023 下午 5:15
 * email : vieper0714@outlook.com
 */
public interface ArraySortAlgorithmTemplate extends AlgorithmTemplate {


  int default_length = 1000, default_min = 0, default_max = 10000;

  /**
   * 生成随机数组
   *
   * @return 随机数组
   */
  default int[] randomIntArr() {
    return AlgoUtils.randomIntArr(default_length, default_min, default_max);
  }


  /**
   * 排序方法
   *
   * @param arr   待排序数组
   * @param left  左边界
   * @param right 右边界
   */
  void sort(int[] arr, int left, int right);


}
