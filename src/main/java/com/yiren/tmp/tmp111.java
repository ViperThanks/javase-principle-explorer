package com.yiren.tmp;

import cn.hutool.core.util.NumberUtil;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.expand.BaseTest;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * desc:
 *
 * @author weilin
 * @since 2024-1-2
 */
public class tmp111 extends BaseTest implements Explorer {

  public static void main(String[] args) {
    Executor.executeExplorer(tmp111.class);
  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    int j123 = NumberUtils.toInt("j123", 0);
    println(j123);
  }
}
