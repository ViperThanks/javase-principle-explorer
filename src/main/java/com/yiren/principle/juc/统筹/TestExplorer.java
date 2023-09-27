package com.yiren.principle.juc.统筹;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class TestExplorer implements Explorer {
  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Thread thread = new Thread(() -> {

    });
  }

  public static void main(String[] args) {
    Executor.executeExplorer(TestExplorer.class);
  }
}
