package com.yiren.utils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 计时器,用于计算任务执行时间
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class StopWatch {

  /**
   * 获取任务执行时间,单位纳秒
   * @param task 任务
   * @return 任务执行时间
   */
  public static long calculateNanoseconds(Runnable task) {
    return calculate0(task, TimeUnit.NANOSECONDS);
  }

  /**
   * 获取任务执行时间,单位毫秒
   * @param task 任务
   * @return 任务执行时间
   */
  public static long calculateMilliseconds(Runnable task) {
    return calculate0(task, TimeUnit.MILLISECONDS);
  }


  /**
   * 获取任务执行时间,单位 timeUnit
   * @param task 任务
   * @return 任务执行时间
   */
  public static long calculate(Runnable task, TimeUnit timeUnit) {
    return calculate0(task, timeUnit);
  }

  /**
   * 获取任务执行时间,单位 timeUnit
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
