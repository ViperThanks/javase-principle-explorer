package com.yiren.principle.juc.Sync原理;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc: 举个例子，前面讲了偏向锁和轻量级锁，那么请问，如果只是轻量级锁，还没有申请monitor的时候调用wait是否会直接锁膨胀，如果不膨胀那么如何另线程阻塞
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class LockExpansionExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(LockExpansionExplorer.class);


  public static final Object lock = new Object();
  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override

  public void explore() throws Exception {
    //直接轻量级锁
    new Thread(() -> {
      synchronized (lock) {
        LOGGER.debug(ClassLayout.parseInstance(lock).toPrintable());
        //now wait
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    },"t1").start();
    Sleeper.sleep(3);
    LOGGER.debug(ClassLayout.parseInstance(lock).toPrintable());
    LOGGER.debug(ClassLayout.parseInstance(Object.class).toPrintable());
  }

  public static void main(String[] args) {
    Executor.executeExplorer(LockExpansionExplorer.class);
  }


}