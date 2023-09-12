package com.yiren.tmp;

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
 * @since 31/8/2023
 */
public class QiaoDaiMaShiGanMaDe implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(QiaoDaiMaShiGanMaDe.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    log.info("敲代码是这样的 ");
    System.exit(5000000);

  }

  public static void main(String[] args) {
    Executor.executeExplorer(QiaoDaiMaShiGanMaDe.class);
  }


}