package com.yiren.principle.juc.线程的状态;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class FiveStatus implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(FiveStatus.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    // normal
    Thread thread = new Thread(() -> {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }, "t1");
    log.info(thread.getState().name());
    thread.start();
    log.info(thread.getState().name());
    log.info(thread.getState().name());
    Thread.sleep(100L);
    log.info(thread.getState().name());
    thread.interrupt();
    log.info(thread.getState().name());
    Thread.sleep(1000L);
    log.info(thread.getState().name());
    // interrupter


  }

  public static void main(String[] args) {

    Executor.executeExplorer(FiveStatus.class);
  }


}