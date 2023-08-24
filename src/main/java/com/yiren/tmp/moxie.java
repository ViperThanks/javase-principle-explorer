package com.yiren.tmp;

import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.principle.javase.object.ObjectBytesDetailExplorer;
import com.yiren.core.Executor;
import com.yiren.utils.Printer;

import java.util.Arrays;


/**
 * desc  :
 * author: weilin
 * date  : 1/8/2023
 */
public class moxie {

  public static void main(String[] args) {
    //默写 快排
    int[] ints = AlgoUtils.randomIntArr(1000, 0, 10000);
    int[] ints1 = Arrays.copyOf(ints, ints.length);
    Printer.print(ints1 == ints);
    //new QuickSort().sort(ints,0,ints.length - 1);
    Arrays.sort(ints1);
    quick_sort(ints, 0, ints.length - 1);
    Printer.print(Arrays.equals(ints1, ints));

    Executor.executeExplorer(new ObjectBytesDetailExplorer());
    Executor.executeExplorer(ObjectBytesDetailExplorer.class);
  }

  private static void quick_sort(int[] a, int l, int r) {
    if (l >= r)
      return;
    int x = a[l + r >> 1], i = l - 1, j = r + 1;
    while (i < j) {
      do i++; while (a[i] < x);
      do j--; while (a[j] > x);
      if (i < j) {
        AlgoUtils.swap(a, i, j);
      }
    }

    quick_sort(a, l, j);
    quick_sort(a, j + 1, r);


  }

}
