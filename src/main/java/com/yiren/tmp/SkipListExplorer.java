package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.entity.PrincipleField;
import com.yiren.principle.utils.PrincipleUtil;
import com.yiren.utils.Memory;
import com.yiren.utils.MemoryUtil;

import java.util.Arrays;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-10-30
 */
public class SkipListExplorer extends BaseExplorer {

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    byte[] bytes = MemoryUtil.getBytes(1, Memory.MB);
    log.info(Arrays.toString(bytes));
    PrincipleField<?> length = PrincipleUtil.getFiled(bytes, "length");

    log.info(Arrays.toString(bytes));
  }


  public static void main(String[] args) {
    Executor.executeMyself();
  }
}
