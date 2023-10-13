package com.yiren.algorithm.utils;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>
 * desc    : 待定，主要写python的一些实现，无聊写的
 * </h2>
 *
 * @author : weilin
 * @since : 26/8/2023
 */
public class Python {

  public static int[] range(int from, int to, int step) {
    return AlgoUtils.range(from, to, step);
  }

  public static int[] range(int from, int to) {
    return AlgoUtils.range(from, to);
  }

  public static int[] range(int to) {
    return AlgoUtils.range(to);
  }

  public static List<Integer> range(Integer from, Integer to, Integer step) {
    return Arrays.stream(range(from.intValue(), to.intValue(), step.intValue()))
        .boxed()
        .collect(Collectors.toList());
  }

  public static List<Integer> range(Integer from, Integer to) {
    return range(from, to, Integer.valueOf(1));
  }

  public static List<Integer> range(Integer to) {
    return range(Integer.valueOf(0), to, Integer.valueOf(1));
  }


}

