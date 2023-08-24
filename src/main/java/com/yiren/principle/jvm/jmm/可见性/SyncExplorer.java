package com.yiren.principle.jvm.jmm.可见性;

import com.yiren.core.Explorer;
import com.yiren.core.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc  :
 * author: weilin
 * date  : 17/8/2023
 */
public class SyncExplorer implements Explorer {


  private static final Logger log = LoggerFactory.getLogger(SyncExplorer.class);

  static int x;


  @Override
  public void explore() throws Exception {
    new Thread(() -> {
      x = 100;
    }, "t1").start();

    new Thread(() -> {
      log.info(String.valueOf(x));
    }, "t2").start();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(SyncExplorer.class);
  }
}
