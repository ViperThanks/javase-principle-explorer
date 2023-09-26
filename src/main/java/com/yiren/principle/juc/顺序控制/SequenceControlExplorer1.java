package com.yiren.principle.juc.顺序控制;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc: 基于synchronized 关键帧解决， 保护性暂停模式
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class SequenceControlExplorer1 implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SequenceControlExplorer1.class);


  static final Object lock = new Object();

  static volatile boolean flag = false;

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Thread t1 = new Thread(() -> {
      synchronized (lock){
        while (!flag) {
          try {
            lock.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
        LOGGER.debug(String.valueOf(1));
      }
    }, "t1");

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        LOGGER.debug(String.valueOf(2));
        flag = true;
        lock.notify();
      }
    }, "t2");

    t1.start();
    t2.start();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(SequenceControlExplorer1.class);
  }


}