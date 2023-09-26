package com.yiren.principle.juc.无锁并发cas;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.utils.Memory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class CasExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(CasExplorer.class);


  private static final AtomicInteger money = new AtomicInteger(0);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {

  }

  public static void main(String[] args) {
    Executor.executeExplorer(CasExplorer.class);
  }


}