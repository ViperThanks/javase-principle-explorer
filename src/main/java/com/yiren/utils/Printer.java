package com.yiren.utils;

import com.yiren.algorithm.datastructure.ListNode;
import com.yiren.algorithm.datastructure.Pair;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * desc  : 打印工具类，底层就是用的System.out.println
 * 重载了多个print方法，可以打印任意类型的数据
 * author: weilin
 * date  : 17/7/2023
 */
public class Printer {
  /**
   * 标准系统输出流
   */
  private static final PrintStream stdOutStream = System.out;
  /**
   * 标准系统错误输出流
   */
  private static final PrintStream stdErrStream = System.err;

  /**
   * 分隔符
   */
  private static final String SEPARATOR =
    "-----------------------this is a separator line power by com.yiren.utils.Printer----------------------";

  /**
   * 默认占位符
   */
  private static final String DEFAULT_PLACEHOLDER = "{}";
  private static final String DEFAULT_END = "\n";

  //ensure Printer is not instantiated
  private Printer() {
  }

  public static void printSeparator() {

    stdOutStream.println("\n" + SEPARATOR + "\n");
  }

  /**
   * 使用方式
   * print(“{} ..... {}”, arg1, arg2, arg3, arg4)
   *
   * @param msg  信息带有占位符 {}
   * @param args 参数
   */
  public static void print(String msg, Object... args) {
    print0(msg, DEFAULT_END, args);
  }

  /**
   * 打印 obj 如果 obj 是数组或者自定义的数据结构比如{@linkplain ListNode}，会打印每个元素
   *
   * @param obj 任意对象
   */
  public static void print(Object obj) {
    print0(DEFAULT_PLACEHOLDER, DEFAULT_END, obj);
  }

  /**
   * 打印 obj 如果 obj 是数组或者自定义的数据结构比如{@linkplain ListNode}，会打印每个元素
   *
   * @param obj 任意对象
   */
  public static void print(Object obj,@Nonnull String end) {
    print0(DEFAULT_PLACEHOLDER, end, obj);
  }


  public static void print(String msg) {
    stdOutStream.println(msg);
  }


  /**
   * 打印
   * 时间复杂度 O(n)
   *
   * @param msg  信息
   * @param args 参数
   */
  private static void print0(@Nullable String msg, String end, @Nonnull Object... args) {
    if (StringUtils.isBlank(msg)) {
      return;
    }
    final StringBuilder sb = new StringBuilder(msg);
    int index = 0;
    for (Object arg : args) {
      index = sb.indexOf(DEFAULT_PLACEHOLDER, index);
      if (index == -1) {
        // finish
        break;
      }
      //内部动态实现
      String argString = PrinterHelper.toString(arg);
      sb.replace(index, index + 2, argString);
      index += argString.length();
    }
    if (end.equals("\n")) {
      stdOutStream.println(sb.toString());
    } else {
      stdOutStream.print(sb + end);
    }
  }


  /**
   * 确保参数个数和占位符个数一致
   * 时间复杂度O(n)
   *
   * @param msg  信息
   * @param args 参数
   */
  @Deprecated
  private static void ensureCorresponding(String msg, Object... args) {
    final int count = StringUtils.countMatches(msg, DEFAULT_PLACEHOLDER);
    if (count != args.length) {
      stdErrStream.println("print方法参数个数不匹配");
      throw new IllegalArgumentException("参数个数不匹配, " + DEFAULT_PLACEHOLDER + "有" + count + "个, 但是参数有" + args.length + "个");
    }
  }


  private static class PrinterHelper {
    /**
     * 链表节点之间的分隔符
     */
    private static final String LIST_NODE_SEPARATOR = " -> ";

    /**
     * 已重载打印的类型
     */
    private static final Class<?>[] supportClass =
      {
        ListNode.class,
        Pair.class,
        int[].class,
      };


    //ensure supportClass is sorted
    // accelerate search
    private static final String[] supportClassStr = Arrays.stream(supportClass)
      .map(Class::getSimpleName)
      .sorted()
      .toArray(String[]::new);


    private static boolean isSupport(@Nonnull Class<?> clazz) {
      return Arrays.binarySearch(supportClassStr, clazz.getSimpleName()) >= 0;
    }


    /**
     * 将对象转换为字符串
     *
     * @param obj 对象
     * @return 字符串
     */
    private static String toString(@Nullable Object obj) {
      if (obj == null) {
        return "null";
      }
      final Class<?> aClass = obj.getClass();
      if (!isSupport(aClass)) {
        return obj.toString();
      }
      //反射去调用
      Object res = null;
      try {
        final Method toString = PrinterHelper.class.getDeclaredMethod("toString0", aClass);
        res = toString.invoke(null, obj);
      } catch (Exception e) {
        stdErrStream.println("反射调用toString方法失败");
      }
      return (String) res;
    }

    /**
     * 将链表转换为字符串
     * 效果如下:
     * null -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     */
    private static String toString0(@Nullable ListNode node) {
      //ensure node is not null
      if (node == null) {
        return "null";
      }
      //null ->
      final StringBuilder sb = new StringBuilder("null" + LIST_NODE_SEPARATOR);
      while (node != null) {
        sb.append(node.val).append(LIST_NODE_SEPARATOR);
        node = node.next;
      }
      //null -> ... -> null
      sb.append("null");
      return sb.toString();
    }

    /**
     * 将pair转换为字符串
     * @param pair
     * @return
     */
    private static <K, V> String toString0(@Nullable Pair<K, V> pair) {
      if (pair == null) {
        return "null";
      }
      return pair.toString();
    }

    private static String toString0(@Nullable int[] arr) {
      return Arrays.toString(arr);
    }

  }
}
