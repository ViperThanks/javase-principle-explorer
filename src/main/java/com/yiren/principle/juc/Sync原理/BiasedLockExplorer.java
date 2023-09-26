package com.yiren.principle.juc.Sync原理;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.openjdk.jol.info.ClassLayout;
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
public class BiasedLockExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(BiasedLockExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    //让可偏向生效 经过实验大概在4s左右
    Sleeper.sleep(4);
    final Object lock = new Object();
    log.info(ClassLayout.parseInstance(lock).toPrintable());
    new Thread(() -> {
      synchronized (lock) {
      //偏向锁
      log.info(ClassLayout.parseInstance(lock).toPrintable());
      //但是我这里wait方法了，所以偏向锁会升级为重量级锁，因为只有重量级锁才会有wait notify，monitor
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        log.info(ClassLayout.parseInstance(lock).toPrintable());
    }
    }, "t1").start();

    Sleeper.sleep(2);
    synchronized (lock) {
      lock.notify();
      log.info(ClassLayout.parseInstance(lock).toPrintable());
    }

  }

  public static void main(String[] args) {
    Executor.executeExplorer(BiasedLockExplorer.class);
  }


}