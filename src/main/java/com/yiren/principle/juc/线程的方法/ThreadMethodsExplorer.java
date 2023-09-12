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

public class ThreadMethodsExplorer implements Explorer {
  private static final Logger log = LoggerFactory.getLogger(ThreadMethodsExplorer.class);


  /**
   * 探索 方法
   * @throws Exception
   */
  Thread.State state;
  @Override
  public void explore() throws Exception {
    Thread thread = new Thread(() -> {
      log.info("thread start");
      log.info("thread end");
    },"t1");
    state = thread.getState();
    log.info("{}", state);
    thread.start();
    state = thread.getState();
    log.info("{}", state);
    TimeUnit.SECONDS.sleep(1);
    state = thread.getState();
    log.info("{}", state);



  }


  public static void main(String[] args) {
    Executor.executeExplorer(ThreadMethodsExplorer.class);
  }
}
