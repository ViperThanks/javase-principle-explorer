package com.yiren.tmp;

import com.yiren.algorithm.datastructure.Pair;
import com.yiren.utils.Printer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */
public class JavaTest {

  private static final Logger log = LoggerFactory.getLogger(JavaTest.class);


  public static void main(String[] args) {
    Queue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
    queue.add(new Pair<>(1, 2));
    queue.add(new Pair<>(2, 3));
    queue.add(new Pair<>(3, 4));
    queue.add(new Pair<>(4, 5));
    queue.add(new Pair<>(5, 6));
    queue.add(new Pair<>(6, 7));
    queue.add(new Pair<>(7, 8));
    Printer.print("{}", queue);
  }

  @Test
  public void test() {
    System.out.println(toBin(0.3));

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


}

