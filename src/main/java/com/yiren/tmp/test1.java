package com.yiren.tmp;


import cn.hutool.core.bean.BeanUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import com.google.common.math.DoubleMath;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-27
 */
public class test1 extends BaseExplorer {

  private static final Cache<String, Long> cache = CacheBuilder.newBuilder()
      .maximumWeight(15)
      .weigher((Weigher<String, Long>) (key, value) -> key.length())
      .expireAfterWrite(1, TimeUnit.SECONDS)
      .build();

  public static void main(String[] args) {
    Executor.executeExplorer(test1.class);

  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    double a = 8.1 / 3;
    double b = 2.7;

    LOGGER.info("{} = {} is {}", a, b, DoubleMath.fuzzyEquals(a, b, 1e-7));
  }


}
