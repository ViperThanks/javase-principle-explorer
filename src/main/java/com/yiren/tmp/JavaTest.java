package com.yiren.tmp;

import com.google.common.collect.Lists;
import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.principle.javase.零拷贝技术.ZeroCopyExplorer;
import com.yiren.utils.Performance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */
public class JavaTest {

  private static final Logger log = LoggerFactory.getLogger(JavaTest.class);


  public static void main(String[] args) {
    ZeroCopyExplorer explorer = new ZeroCopyExplorer();
    Runnable zeroCopy = () -> {
      try {
        explorer.zeroCopy();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
    Runnable normalCopy = () -> {
      try {
        explorer.normalCopy();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
    Performance.run(zeroCopy).toDetailPrintable();
    Performance.run(normalCopy).toDetailPrintable();
  }

  @Test
  public void test() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
    list.remove(Integer.valueOf(1));
    log.info("list:{}", list);
  }

  String toBin(double num) {
    StringBuilder sb = new StringBuilder();
    sb.append("0.");
    while (num > 0) {
      num *= 2;
      if (num >= 1) {
        sb.append(1);
        num -= 1;
      } else {
        sb.append(0);
      }
      if (sb.length() > 32) {
        return "ERROR:" + sb.toString();
      }
    }
    return sb.toString();
  }

  @Test
  public void test1() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] args = reader.readLine().split(" ");
    int[] arr = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      arr[i] = Integer.parseInt(args[i]);
    }
    int k = Integer.parseInt(args[args.length - 1]);
  }

  @Test
  public void test2() throws IOException {
    BufferedReader reader = AlgoUtils.getBufferedReader();
    Integer[] split = AlgoUtils.split(reader.readLine(), Integer::parseInt);
    log.info(Arrays.toString(split));
  }


}

