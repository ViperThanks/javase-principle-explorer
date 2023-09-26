package com.yiren.principle.juc.可见性;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class NeverStopExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(NeverStopExplorer.class);

  //如果没有加volatile 则会无限循环，线程的可见性问题
  //volatile
  static boolean run = true;

  static AtomicBoolean runed = new AtomicBoolean(true);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    new Thread(() -> {
      while (run) {
        //cpu 空转
        System.out.print("");
      }
      LOGGER.debug("finish");
    }).start();

    Sleeper.sleep();

    run = false;
    LOGGER.debug("run = [{}] ", run);
  }

  public static void main(String[] args) {
    Executor.executeExplorer(NeverStopExplorer.class);
  }


}