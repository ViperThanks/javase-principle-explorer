package com.yiren.principle.juc.两阶段终止模式;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 31/8/2023
 */
public class TwoPhaseTermination implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(TwoPhaseTermination.class);


  Thread monitor;

  /**
   * 启动监控线程
   */
  public void start() {
    monitor = new Thread(() -> {
      while (true){
        Thread currentThread = Thread.currentThread();
        if (currentThread.isInterrupted()){
          log.debug("料理后事");
          break;
        }

        try {
          TimeUnit.SECONDS.sleep(1);
          log.debug("好好好");
        } catch (InterruptedException e) {
          e.printStackTrace();
          currentThread.interrupt();
        }
      }
    }, "monitor");
    monitor.start();
  }

  /**
   * 终止监控线程
   */
  public void stop() {
    monitor.interrupt();
  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    start();
    TimeUnit.SECONDS.sleep(3);
    stop();
  }

  public static void main(String[] args) {
    LockSupport.park();
    Executor.executeExplorer(TwoPhaseTermination.class);
  }


}