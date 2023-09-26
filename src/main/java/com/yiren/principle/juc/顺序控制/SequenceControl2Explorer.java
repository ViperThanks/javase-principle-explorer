package com.yiren.principle.juc.顺序控制;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc: park & unpark
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class SequenceControl2Explorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SequenceControl2Explorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {

  }

  public static void main(String[] args) {
    Executor.executeExplorer(SequenceControl2Explorer.class);
  }


}