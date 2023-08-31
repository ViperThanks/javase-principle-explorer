package com.yiren.algorithm.utils;

import com.google.common.collect.Lists;
import com.yiren.algorithm.datastructure.ListNode;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * desc  : 算法(algo)工具类, 用来生成随机数组和基本的swap等简单方法
 * 所有的随机生成都是依靠{@link ThreadLocalRandom}来生成
 * author: wl
 * date  : 24/6/2023 下午 5:29
 * email : vieper0714@outlook.com
 */
public class AlgoUtils {

  private static final ThreadLocalRandom random = ThreadLocalRandom.current();

  /**
   * 交换数组中的两个元素
   * 确保知道越界的后果
   *
   * @param arr 数组
   * @param i   i 要交换的元素坐标
   * @param j   j 要交换的元素坐标
   */
  public static void swap(@Nonnull int[] arr, int i, int j) {
    int x = arr[i];
    arr[i] = arr[j];
    arr[j] = x;
  }

  /**
   * 交换数组中的两个元素
   * 确保知道越界的后果
   *
   * @param arr 数组
   * @param i   i 要交换的元素坐标
   * @param j   j 要交换的元素坐标
   */
  public static void swap1(@Nonnull int[] arr, int i, int j) {
    //确保不是同一地址,又因为数组寻址法，只需要i!=j即可
    if (i == j) return;
    arr[i] = arr[i] ^ arr[j] ^ (arr[j] = arr[i]);
  }

  /**
   * 生成随机数组,int 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static int[] randomIntArr(int length) {
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextInt(100);
    }
    return arr;
  }

  /**
   * 生成随机数组,int 型
   *
   * @param length 长度
   * @param from   起始值
   * @param to     结束值
   * @return 随机数组
   */
  public static int[] randomIntArr(int length, int from, int to) {
    requireState(from <= to, "from must less than to");
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextInt(from, to);
    }
    return arr;
  }


  /**
   * 模仿 python的 range 关键字
   * int 型 ,默认步长为1
   *
   * @param to 结束值
   * @return 连续数组
   */
  public static int[] range(int to) {
    return range(0, to, 1);
  }


  /**
   * 模仿 python的 range 关键字
   * int 型 ,默认步长为1
   *
   * @param from 起始值
   * @param to   结束值
   * @return 连续数组
   */
  public static int[] range(int from, int to) {
    return range(from, to, 1);
  }

  /**
   * 模仿 python的 range 关键字
   *
   * @param from 起始值
   * @param to   结束值
   * @param step 步长
   * @return 连续数组
   */
  public static int[] range(int from, int to, int step) {
    requireState(step != 0, "step must not be zero");
    int[] arr;
    int index = 0;
    if (step > 0) {
      requireState(from <= to, "from must less than to");
      arr = new int[(to - from) / step];
      for (int i = from; i < to; i += step) {
        arr[index++] = i;
      }
    } else {
      requireState(from >= to, "from must greater than to");
      arr = new int[(from - to) / step + 1];
      for (int i = from; i > to; i += step) {
        arr[index++] = i;
      }
    }
    return arr;
  }


  /**
   * 生成随机数组,double 型
   *
   * @param length 长度
   * @param from   起始值
   * @param to     结束值
   * @return 随机数组
   */
  public static double[] randomDoubleArr(double from, double to, int length) {
    requireState(from <= to, "from must less than to");
    double[] arr = new double[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextDouble(from, to);
    }
    return arr;
  }


  /**
   * 生成随机数组,double 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static double[] randomDoubleArr(int length) {
    double[] arr = new double[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextDouble();
    }
    return arr;
  }

  /**
   * 生成随机数组,float 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static float[] randomFloatArr(int length) {
    float[] arr = new float[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextFloat();
    }
    return arr;
  }

  /**
   * 生成随机数组,long 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static long[] randomLongArr(int length) {
    long[] arr = new long[length];
    for (int i = 0; i < length; i++) {
      arr[i] = random.nextLong();
    }
    return arr;
  }

  /**
   * 生成指定ListNode
   */
  public static ListNode asListNode(int... values) {
    ListNode head = new ListNode(values[0]);
    ListNode cur = head;
    for (int i = 1; i < values.length; i++) {
      cur.next = new ListNode(values[i]);
      cur = cur.next;
    }
    return head;
  }


  public static int[] asArray(int... values) {
    return values;
  }

  @SafeVarargs
  public static <T> List<T> asList(T... values) {
    return Lists.newArrayList(values);
  }


  public static boolean isSorted(int[] arr) {
    return isSorted(arr, 0, arr.length - 1);
  }

  public static boolean isSorted(int[] arr, int from, int to) {
    requireState(from <= to, "from must less than to");
    for (int i = from; i < to; i++) {
      if (arr[i] > arr[i + 1]) {
        return false;
      }
    }
    return true;
  }


  /**
   * 确保表达式为true
   */
  static void requireState(boolean expression, String message) {
    if (!expression) {
      throw new IllegalStateException(message);
    }
  }


  public static BufferedReader getBufferedReader() {
    return new BufferedReader(new InputStreamReader(System.in));
  }

  public static BufferedWriter getBufferedWriter() {
    return new BufferedWriter(new OutputStreamWriter(System.out));
  }

  private static final String DEFAULT_REGEX = " ";

  public static <T> T[] split(String str, Function<String, T> function) {
    return split(str, DEFAULT_REGEX, function);
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] split(String str, String regex, Function<String, T> function) {
    String[] split = str.split(regex);
    T[] res = (T[]) Array.newInstance(function.apply(split[0]).getClass(), split.length);
    for (int i = 0; i < split.length; i++) {
      res[i] = function.apply(split[i]);
    }
    return res;
  }

}
