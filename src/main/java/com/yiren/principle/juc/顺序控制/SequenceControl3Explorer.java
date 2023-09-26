package com.yiren.principle.juc.顺序控制;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc: 顺序输出 abcabcabcabc
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class SequenceControl3Explorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SequenceControl3Explorer.class);



  static final Object lock = new Object();
  static volatile int seq = 1;

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    Thread aThread = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        synchronized (lock) {
          while (seq != 1) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
          LOGGER.debug("a");
          seq = 2;
          lock.notifyAll();
        }
      }
    }, "a");

    Thread bThread = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        synchronized (lock) {
          while (seq != 2) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
          LOGGER.debug("b");
          seq = 3;
          lock.notifyAll();
        }
      }
    }, "b");

    Thread cThread = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        synchronized (lock) {
          while (seq != 3) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
          LOGGER.debug("c");
          seq = 1;
          lock.notifyAll();
        }
      }
    }, "c");

    aThread.start();
    bThread.start();
    cThread.start();


  }

  public static void main(String[] args) {
    Executor.executeExplorer(SequenceControl3Explorer.class);
  }


}