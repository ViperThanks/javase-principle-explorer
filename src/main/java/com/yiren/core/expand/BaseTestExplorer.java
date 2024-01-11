package com.yiren.core.expand;

import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: 基本的测试探索类
 *
 * @author weilin
 * @since 2023-12-23
 */
public abstract class BaseTestExplorer extends BaseTest implements Explorer {
  /**
   * 日志
   */
  public final Logger LOGGER = LoggerFactory.getLogger(getClass());
}
