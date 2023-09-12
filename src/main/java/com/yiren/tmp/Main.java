package com.yiren.tmp;

import com.google.common.collect.ImmutableMap;
import com.yiren.algorithm.array.ArraySortAlgorithmTemplate;
import com.yiren.algorithm.array.QuickSort;
import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.core.Executor;
import com.yiren.principle.javase.object.ObjectBytesDetailExplorer;
import com.yiren.utils.Performance;
import com.yiren.utils.performance.core.Stopwatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;

/**
 * desc  :
 * author: wl
 * date  : 17/6/2023 下午 1:39
 * email : vieper0714@outlook.com
 */

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) throws IOException {

    List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
    gcs.forEach(gc ->  log.info(gc.getName()));
  }


  public static void test_arr2list() {
    Map<String, String> map = new Hashtable<>();
    Thread t = new Thread();

    System.out.println(map);
  }


}
