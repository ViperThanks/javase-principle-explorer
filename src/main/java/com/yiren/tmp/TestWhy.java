package com.yiren.tmp;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.Immutable;
import com.yiren.entity.User;
import com.yiren.utils.CommonUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import javax.sound.midi.Soundbank;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class TestWhy extends CommonUtils{

  private static final Logger LOGGER = LoggerFactory.getLogger(TestWhy.class);


  public static void main(String[] args) {
    int[] randomIntArr = getRandomIntArr(100);


  }

  private static String wrapStr(Object o) {
    return o == null ? StringUtils.EMPTY : o.toString();
  }
}

class Solution {

  public long pickGifts(int[] gifts, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>(gifts.length, (o1, o2) -> -o1.compareTo(o2));

    for (int gift : gifts) {
      queue.offer(gift);
    }
    for (int i = 0; i < k; i++) {
      if (!queue.isEmpty()) {
        Integer poll = queue.poll();
        double sqrt = Math.sqrt(poll);
        queue.offer((int) sqrt);
      }
    }
    long res = 0;
    for (Integer i : queue) {
      res += i;
    }
    return res;
  }
}