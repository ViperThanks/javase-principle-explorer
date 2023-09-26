package com.yiren.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class PrintSequenceFrom1To100 {

  private static final Logger LOGGER = LoggerFactory.getLogger(PrintSequenceFrom1To100.class);


  static volatile int num = 1;

  static final Object lock = new Object();

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.execute(new Task1());
    pool.execute(new Task2());
  }

  /**
   * 负责奇数
   */
  static class Task1 implements Runnable {
    @Override
    public void run() {
      while (num < 100) {
        synchronized (lock) {
          if (num % 2 == 0) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
          LOGGER.debug(String.valueOf(num));
          num++;
          lock.notifyAll();
        }
      }
    }
  }

  /**
   * 负责偶数
   */
  static class Task2 implements Runnable {
    @Override
    public void run() {
      while (num < 100) {
        synchronized (lock) {
          if (num % 2 != 0) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
          LOGGER.debug(String.valueOf(num));
          num++;
          lock.notifyAll();
        }
      }
    }
  }
}
