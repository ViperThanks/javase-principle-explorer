package com.yiren.principle.juc.Sync原理;

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
 * @since 6/9/2023
 */
public class SyncByteCodeExplorer implements Explorer {
  static int counter = 0;
  static final Object lock = new Object();
  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    synchronized (lock) {
      counter++;
    }
  }
  public static void main(String[] args) {
    Executor.executeExplorer(SyncByteCodeExplorer.class);
  }
}