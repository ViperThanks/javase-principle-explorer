package com.yiren.tmp;

import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.utils.Printer;

import java.util.Optional;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */
public class JavaTest {

  public static void main(String[] args) {
    int[] ints = AlgoUtils.sortedIntArr(1, 20,2);
    Printer.print(ints);
    Optional<int[]> optional = Optional.of(ints);


  }
}
