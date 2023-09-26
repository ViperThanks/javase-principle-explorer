package com.yiren.principle.juc.锁的活跃性.死锁;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import com.yiren.principle.utils.SyncLock;
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
public class DeadLockExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeadLockExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    final Object a = new SyncLock(1,"a");
    final Object b = new SyncLock(2,"b");

    Thread t1 = new Thread(() -> {
      synchronized (a) {
        LOGGER.info("i get lock : {}", a);
        Sleeper.sleep(0.5);
        synchronized (b) {
          LOGGER.info("i get lock : {}", b);
        }
      }
    }, "t1");

    Thread t2 = new Thread(() -> {
      synchronized (b) {
        LOGGER.info("i get lock : {}", b);
        Sleeper.sleep(0.5);
        synchronized (a) {
          LOGGER.info("i get lock : {}", a);
        }
      }
    }, "t2");
    t2.start();
    t1.start();

  }
  public static void main(String[] args) {
    Executor.executeExplorer(DeadLockExplorer.class);
  }

}