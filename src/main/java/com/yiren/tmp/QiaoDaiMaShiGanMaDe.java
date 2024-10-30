package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.utils.CommonUtils;

import java.util.HashMap;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 31/8/2023
 */
public class QiaoDaiMaShiGanMaDe extends BaseExplorer {


  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    log.info("敲代码是这样的 ");
    HashMap<Object, Object> hashMap = CommonUtils.defaultIfNull(null, HashMap::new);
  }

  public static void main(String[] args) {
    Executor.executeMyselfWithTime();
  }


}