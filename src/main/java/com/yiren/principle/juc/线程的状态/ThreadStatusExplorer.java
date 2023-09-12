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
public class ThreadStatusExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(ThreadStatusExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Thread t1 = new Thread("t1") {
      @Override
      public void run() {
        log.debug("running...");
      }
    };

    Thread t2 = new Thread("t2") {
      @Override
      public void run() {
        while(true) { // runnable

        }
      }
    };
    t2.start();

    Thread t3 = new Thread("t3") {
      @Override
      public void run() {
        log.debug("running...");
      }
    };
    t3.start();

    Thread t4 = new Thread("t4") {
      @Override
      public void run() {
        synchronized (ThreadStatusExplorer.class) {
          try {
            Thread.sleep(1000000); // timed_waiting
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    t4.start();

    Thread t5 = new Thread("t5") {
      @Override
      public void run() {
        try {
          t2.join(); // waiting
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    t5.start();

    Thread t6 = new Thread("t6") {
      @Override
      public void run() {
        synchronized (ThreadStatusExplorer.class) { // blocked
          try {
            Thread.sleep(1000000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    t6.start();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.debug("t1 state {}", t1.getState());
    log.debug("t2 state {}", t2.getState());
    log.debug("t3 state {}", t3.getState());
    log.debug("t4 state {}", t4.getState());
    log.debug("t5 state {}", t5.getState());
    log.debug("t6 state {}", t6.getState());
    System.in.read();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(ThreadStatusExplorer.class);
  }


}