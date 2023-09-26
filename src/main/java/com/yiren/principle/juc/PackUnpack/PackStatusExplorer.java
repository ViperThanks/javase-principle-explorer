package com.yiren.principle.juc.PackUnpack;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public class PackStatusExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(PackStatusExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Thread thread = new Thread(LockSupport::park);
    thread.start();
    Sleeper.sleep(100L);
    log.info("thread status:{}", thread.getState());
    Sleeper.sleep(200L);
    LockSupport.unpark(thread);
  }

  public static void main(String[] args) {
    Executor.executeExplorer(PackStatusExplorer.class);
  }


}