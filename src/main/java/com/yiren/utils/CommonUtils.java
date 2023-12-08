package com.yiren.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


/**
 * desc: 日常用的Java工具类 random 都是基于 {@linkplain ThreadLocalRandom#current()}
 *
 * @author weilin
 * @since 2023-12-7
 */
@SuppressWarnings("unused")
public class CommonUtils {

  private CommonUtils() {
    throw new AssertionError("No com.yiren.utils.CommonUtils instances for you!");
  }

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CommonUtils.class);
  private static final String LOGGER_PREFIX = "CommonUtils ---> ";
  /**
   * 默认长度，一般都是生成数组的默认长度
   */
  private static final int DEFAULT_SIZE = 10;

  //--- 生成的方法们 --- START ---


  public static double getRandomDouble(double from, double to) {
    return RandomHolder.RANDOM.nextDouble(from, to);
  }

  public static double getRandomDouble(double to) {
    return RandomHolder.RANDOM.nextDouble(to);
  }

  public static int getRandomInt(int from, int to) {
    return RandomHolder.RANDOM.nextInt(from, to);
  }

  public static int getRandomInt(int to) {
    return RandomHolder.RANDOM.nextInt(to);
  }

  public static long getRandomLong(int from, int to) {
    return RandomHolder.RANDOM.nextLong(from, to);
  }

  public static long getRandomLong(int to) {
    return RandomHolder.RANDOM.nextLong(to);
  }

  /**
   * 生成随机数组,int 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static int[] getRandomIntArr(int length) {
    requirePositive(length, "length");
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextInt(100);
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
  public static int[] getRandomIntArr(int length, int from, int to) {
    requiredStatus(from <= to, "from must less than to");
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextInt(from, to);
    }
    return arr;
  }


  /**
   * 模仿 python的 range 关键字 int 型 ,默认步长为1
   *
   * @param to 结束值
   * @return 连续数组
   */
  public static int[] range(int to) {
    return range(0, to, 1);
  }


  /**
   * 模仿 python的 range 关键字 int 型 ,默认步长为1
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
    requiredStatus(from != to && step != 0, "illegal arguments");
    int[] arr;
    int index = 0;
    if (step > 0) {
      requiredStatus(from <= to, "from must less than to");
      arr = new int[(to - from) / step];
      for (int i = from; i < to; i += step) {
        arr[index++] = i;
      }
    } else {
      requiredStatus(from >= to, "from must greater than to");
      arr = new int[(from - to) / (-1 * step)];
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
  public static double[] getRandomDoubleArr(double from, double to, int length) {
    requiredStatus(from <= to, "from must less than to");
    double[] arr = new double[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextDouble(from, to);
    }
    return arr;
  }


  /**
   * 生成随机数组,double 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static double[] getRandomDoubleArr(int length) {
    double[] arr = new double[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextDouble();
    }
    return arr;
  }

  /**
   * 生成随机数组,float 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static float[] getRandomFloatArr(int length) {
    float[] arr = new float[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextFloat();
    }
    return arr;
  }

  /**
   * 生成随机数组,long 型
   *
   * @param length 长度
   * @return 随机数组
   */
  public static long[] getRandomLongArr(int length) {
    long[] arr = new long[length];
    for (int i = 0; i < length; i++) {
      arr[i] = RandomHolder.RANDOM.nextLong();
    }
    return arr;
  }

  //--- 生成的方法们 --- END ---

//-------------------------------------------转换方法-----------------------------------------

  @SafeVarargs
  public static <T> T[] toArray(final T... items) {
    return items;
  }

//-------------------------------------------转换方法-----------------------------------------

  //日常使用的算法方法

  /**
   * 数组交换 i ， j
   */
  public static void swap(Object array, int i, int j) {
    requiredStatus(array != null && array.getClass().isArray(), "array参数不是数组");
    requiredIndex(Array.getLength(array), i, "i");
    requiredIndex(Array.getLength(array), j, "j");
    Object tmp = Array.get(array, i);
    Array.set(array, i, Array.get(array, j));
    Array.set(array, j, tmp);
  }


  /**
   * 基于类的懒加载实现的不用random对象的时候就不创建
   */
  private static final class RandomHolder {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
  }

//-------------------------------------------字符串-----------------------------------------

  /**
   * 字符生成器类，不用就不用加载
   */
  private static final class StringGenerator {

    /**
     * 字母表
     */
    private static final char[] Alphabet = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    };

    /**
     * 字母+数字表
     */
    private static final char[] AlphabetPlusDigital = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static String generateString(int size) {
      return generateString(size, false);
    }

    private static String generateString(int size, boolean includeNumber) {
      return includeNumber ? generateString(AlphabetPlusDigital, size) : generateString(Alphabet, size);
    }

    private static String generateString(String baseContext, int size) {
      return generateString(baseContext.toCharArray(), size);
    }

    private static String generateString(char[] baseCharArray, int size) {
      requirePositive(size, "size");
      int index = baseCharArray.length;
      StringBuilder sb = new StringBuilder(size);
      for (int i = 0; i < size; i++) {
        final int finalIndex = RandomHolder.RANDOM.nextInt(index);
        sb.append(baseCharArray[finalIndex]);
      }
      return sb.toString();
    }
  }

  /**
   * 获取随机的字符串 , 默认不包含数字
   *
   * @return 随机字符串
   * @see StringGenerator#Alphabet
   */
  public static String getRandomString() {
    return StringGenerator.generateString(DEFAULT_SIZE);
  }

  /**
   * 获取随机的字符串 , 默认不包含数字
   *
   * @param size 长度
   * @return 随机字符串
   * @see StringGenerator#Alphabet
   */
  public static String getRandomString(int size) {
    return StringGenerator.generateString(size);
  }

  /**
   * 获取随机的字符串，可选择是否包含数字
   *
   * @param size          长度
   * @param includeNumber 是否包含数字
   * @return 随机字符串
   * @see StringGenerator#AlphabetPlusDigital
   */
  public static String getRandomString(int size, boolean includeNumber) {
    return StringGenerator.generateString(size, includeNumber);
  }

  /**
   * 获取随机的字符串,基于给的baseContext
   *
   * @param size        长度
   * @param baseContext 基本的数据
   * @return 随机字符串
   * @see StringGenerator#Alphabet
   */
  public static String getRandomString(String baseContext, int size) {
    return StringGenerator.generateString(baseContext, size);
  }

  /**
   * 获取随机的字符串,基于baseCharArray
   *
   * @param size          长度
   * @param baseCharArray 基本的数据
   * @return 随机字符串
   * @see StringGenerator#Alphabet
   */
  public static String getRandomString(char[] baseCharArray, int size) {
    return StringGenerator.generateString(baseCharArray, size);
  }

//-------------------------------------------字符串-----------------------------------------

//-------------------------------------------底层探索用的-----------------------------------------

  /**
   * 原理帮助类，最主要是为了探索原理用的，比如 常用的Unsafe类
   *
   * @see sun.misc.Unsafe
   */
  private static final class PrincipleHelper {

    private static final Class<sun.misc.Unsafe> UNSAFE_CLASS = sun.misc.Unsafe.class;
    private static final String UNSAFE_FIELD_NAME = "theUnsafe";

    private static volatile sun.misc.Unsafe unsafe;

    /**
     * 获取unsafe 基于double check lock的懒汉单例 + 反射
     *
     * @return unsafe
     */

    public static sun.misc.Unsafe getUnsafe() {
      if (unsafe == null) {
        synchronized (CommonUtils.class) {
          if (unsafe == null) {
            try {
              Field unsafeField = UNSAFE_CLASS.getDeclaredField(UNSAFE_FIELD_NAME);
              unsafeField.setAccessible(true);
              unsafe = (sun.misc.Unsafe) unsafeField.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
              LOGGER.error(LOGGER_PREFIX + "反射获取失败，原因是JDK8之后反射麻烦了");
              throw new RuntimeException(e);
            }
          }
        }
      }
      return unsafe;
    }
  }

  public static sun.misc.Unsafe getUnsafe() {
    return PrincipleHelper.getUnsafe();
  }

  //-------------------------------------------底层探索用的-----------------------------------------
//-------------------------------------------流的封装-----------------------------------------
  private static final class StreamWrapper {

    private static BufferedReader wrap(InputStream is) {
      return new BufferedReader(new InputStreamReader(is));
    }

    private static BufferedWriter wrap(OutputStream os) {
      return new BufferedWriter(new OutputStreamWriter(os));
    }

  }

  public static BufferedReader getBufferedReader(InputStream is) {
    return StreamWrapper.wrap(is);
  }

  public static BufferedWriter getBufferedWriter(OutputStream os) {
    return StreamWrapper.wrap(os);
  }

//-------------------------------------------流的封装-----------------------------------------

//-------------------------------------------睡眠工具类-----------------------------------------

  /**
   * desc: 线程睡眠类，简单的把exception try catch了， 核心方法 sleep 最主要是 因为一般来说 sleep不会到 Long级别的秒数 如果打算interrupt exception不建议使用
   *
   * @author weilin
   * @since 12/9/2023
   */
  private static final class Sleeper {

    /**
     * 默认睡一秒
     */
    private static void sleep() {
      sleep(1);
    }

    /**
     * 睡眠x秒
     *
     * @param x 秒
     */
    private static void sleep(double x) {
      try {
        Thread.sleep((long) (x * 1000L));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    /**
     * 睡眠x秒
     *
     * @param x 秒
     */
    private static void sleep(int x) {
      try {
        Thread.sleep(x * 1000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    /**
     * 睡眠x毫秒
     *
     * @param x 毫秒
     */
    private static void sleep(long x) {
      try {
        Thread.sleep(x);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    private static void sleep(int x, TimeUnit unit) {
      try {
        unit.sleep(x);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void sleep() {
    Sleeper.sleep();
  }

  /**
   * 睡眠x秒
   *
   * @param x 秒
   */
  public static void sleep(double x) {
    Sleeper.sleep(x);
  }

  /**
   * 睡眠x秒
   *
   * @param x 秒
   */
  public static void sleep(int x) {
    Sleeper.sleep(x);
  }

  /**
   * 睡眠x毫秒
   *
   * @param x 毫秒
   */
  public static void sleep(long x) {
    Sleeper.sleep(x);
  }

  public static void sleep(int x, TimeUnit unit) {
    Sleeper.sleep(x, unit);
  }

//-------------------------------------------睡眠工具类-----------------------------------------

//-------------------------------------------检测时间类-----------------------------------------

  /**
   * <p>
   * desc: 计时器,用于计算任务执行时间
   * </p>
   *
   * @author weilin
   * @since 4/9/2023
   */
  private static final class StopWatch {

    /**
     * 获取任务执行时间,单位纳秒
     *
     * @param task 任务
     * @return 任务执行时间
     */
    private static long calculateNanoseconds(Runnable task) {
      return calculate0(task, TimeUnit.NANOSECONDS);
    }

    /**
     * 获取任务执行时间,单位毫秒
     *
     * @param task 任务
     * @return 任务执行时间
     */
    private static long calculateMilliseconds(Runnable task) {
      return calculate0(task, TimeUnit.MILLISECONDS);
    }


    /**
     * 获取任务执行时间,单位 timeUnit
     *
     * @param task 任务
     * @return 任务执行时间
     */
    private static long calculate(Runnable task, TimeUnit timeUnit) {
      return calculate0(task, timeUnit);
    }

    /**
     * 获取任务执行时间,单位 timeUnit
     *
     * @param task 任务
     * @return 任务执行时间
     */
    private static long calculate0(Runnable task, TimeUnit timeUnit) {
      long start = System.nanoTime();

      task.run();

      long end = System.nanoTime();
      return timeUnit.convert(end - start, TimeUnit.NANOSECONDS);
    }
  }

  public static long calculateNanoseconds(Runnable task) {
    return StopWatch.calculateNanoseconds(task);
  }

  public static long calculateMilliseconds(Runnable task) {
    return StopWatch.calculateMilliseconds(task);
  }

  public static long calculate(Runnable task, TimeUnit unit) {
    return StopWatch.calculate(task, unit);
  }

//-------------------------------------------检测时间类-----------------------------------------

//-------------------------------------------检查方法-----------------------------------------

  /**
   * 如果不符合状态就 throw 异常
   *
   * @param expectStatus 期待的状态
   * @param errMessage   错误信息
   */
  public static void requiredStatus(boolean expectStatus, String errMessage) {
    if (!expectStatus) {
      throw new IllegalArgumentException(errMessage);
    }
  }

  /**
   * 如果不符合状态就 throw 异常
   *
   * @param expectStatus 期待的状态
   */
  public static void requiredStatus(boolean expectStatus) {
    if (!expectStatus) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * 如果索引超出界限了就报错
   *
   * @param maxLength 最大值，一般都是... size 或者说 length
   * @param index     索引
   * @param name      名字
   */
  public static void requiredIndex(int maxLength, int index, String name) {
    if (!(index >= 0 && index < maxLength)) {
      throw new ArrayIndexOutOfBoundsException(
          name + " is out of bound because " + index + " not between 0 and " + maxLength);
    }
  }

  /**
   * 要求非负数
   *
   * @param value int型的值
   * @param name  参数的名字
   */
  public static void requiredNonnegative(int value, String name) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }
  }


  /**
   * 要求非负数
   *
   * @param value long型的值
   * @param name  参数名字
   */
  public static void requiredNonnegative(long value, String name) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }
  }

  /**
   * 要求正数
   *
   * @param value int 型的值
   * @param name  参数名字
   */
  public static void requirePositive(int value, String name) {
    if (value <= 0) {
      throw new IllegalArgumentException(name + " must be positive but was: " + value);
    }
  }
//-------------------------------------------检查方法-----------------------------------------
}
