package com.yiren.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    long start = System.nanoTime();

    task.run();

    long end = System.nanoTime();
    return end - start;
  }

  /**
   * 获取任务执行时间,单位毫秒
   * @param task 任务
   * @return 任务执行时间
   */
  public static long calculateMilliseconds(Runnable task) {
    long start = System.currentTimeMillis();

    task.run();

    long end = System.currentTimeMillis();
    return end - start;
  }

}
