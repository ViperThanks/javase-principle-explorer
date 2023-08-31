package com.yiren.principle.juc.线程的方法;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 31/8/2023
 */
public class SleepAndYieldExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(SleepAndYieldExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Thread thread1 = new Thread(() -> {
      log.info("thread1 start");
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      log.info("thread1 end");
    }, "t1");
    thread1.start();
    thread1.interrupt();



  }

  public static void main(String[] args) {
    Executor.executeExplorer(SleepAndYieldExplorer.class);
  }


}