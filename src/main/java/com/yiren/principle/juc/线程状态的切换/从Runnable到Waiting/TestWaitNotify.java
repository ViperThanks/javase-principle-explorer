package com.yiren.principle.juc.线程状态的切换.从Runnable到Waiting;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public class TestWaitNotify implements Explorer {
  private static final Logger log = LoggerFactory.getLogger(TestWaitNotify.class);


  @Override
  public void explore() throws Exception {
    Object lock = new Object();

    new Thread(() -> {
      synchronized (lock) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log.info("{} 被唤醒了", Thread.currentThread().getName());
        log.info("干其他事情");
        Sleeper.sleep(500);
      }
    },"t1").start();

    new Thread(() -> {
      synchronized (lock) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log.info("{} 被唤醒了", Thread.currentThread().getName());
        log.info("干其他事情");
        Sleeper.sleep(500);
      }
    },"t2").start();

    Sleeper.sleep(500L);

    synchronized (lock) {
      lock.notifyAll();
    }
  }

  public static void main(String[] args) {
    Executor.executeExplorer(TestWaitNotify.class);
  }
}
