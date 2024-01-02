package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import java.util.concurrent.ConcurrentSkipListSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-10-30
 */
public class SkipListExplorer implements Explorer {




  private static final Logger LOGGER = LoggerFactory.getLogger(SkipListExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    ConcurrentSkipListSet<Integer> skipListSet = new ConcurrentSkipListSet<>();

  }


  public static void main(String[] args) {
    Executor.executeExplorer(SkipListExplorer.class);
  }
}
