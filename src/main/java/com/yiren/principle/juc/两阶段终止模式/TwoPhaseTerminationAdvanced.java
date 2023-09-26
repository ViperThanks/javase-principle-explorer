package com.yiren.principle.juc.两阶段终止模式;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

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
public class TwoPhaseTerminationAdvanced implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(TwoPhaseTerminationAdvanced.class);


  Thread monitor;

  private volatile boolean stop = false;

  /**
   * 启动监控线程
   */
  public void start() {
    monitor = new Thread(() -> {
      while (true) {
        if (stop) {
          LOGGER.debug("料理后事");
          break;
        }

        try {
          TimeUnit.SECONDS.sleep(1);
          LOGGER.debug("好好好");
        } catch (InterruptedException ignored) {
        }
      }
    }, "monitor");
    monitor.start();
  }

  /**
   * 终止监控线程
   */
  public void stop() {
    stop = true;
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
    Executor.executeExplorer(TwoPhaseTerminationAdvanced.class);
  }


}