package com.yiren.principle.utils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 线程睡眠类，简单的把exception try catch了，
 * 核心方法 sleep 最主要是 因为一般来说 sleep不会到 Long级别的秒数
 * 如果打算interrupt exception不建议使用
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public final class Sleeper {

  private Sleeper() {
  }

  /**
   * 默认睡一秒
   */
  public static void sleep(){
    sleep(1);
  }

  /**
   * 睡眠x秒
   *
   * @param x 秒
   */
  public static void sleep(double x) {
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
  public static void sleep(int x) {
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
  public static void sleep(long x) {
    try {
      Thread.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void sleep(int x, TimeUnit unit) {
    try {
      unit.sleep(x);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
