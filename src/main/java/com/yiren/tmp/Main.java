package com.yiren.tmp;

import com.yiren.algorithm.array.ArraySortAlgorithmTemplate;
import com.yiren.algorithm.array.QuickSort;
import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.core.Executor;
import com.yiren.principle.javase.object.ObjectBytesDetailExplorer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * desc  :
 * author: wl
 * date  : 17/6/2023 下午 1:39
 * email : vieper0714@outlook.com
 */

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) {
    int[] ints = AlgoUtils.randomIntArr(1, 10000, 1000);
    ArraySortAlgorithmTemplate quickSort = new QuickSort();
    quickSort.sort(ints, 0, ints.length - 1);
    Executor.executeExplorer(ObjectBytesDetailExplorer.class);
    Executor.executeAlgo(QuickSort.class);
    Collection<Integer> collection = new ArrayList<>();
    collection.add(1);
    collection.add(2);
    collection.add(3);
    Integer[] array = collection.toArray(new Integer[3]);
    System.out.println(Arrays.toString(array));


  }

  @Test
  public void test_arr2list() {
    Map<String, String> map = new Hashtable<>();
    map.put(null, null);
    map.put(null, null);
    map.put("1", null);
    map.put(null, "1");
    System.out.println(map);
  }


}
