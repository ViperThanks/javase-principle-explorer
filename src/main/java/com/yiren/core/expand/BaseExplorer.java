package com.yiren.core.expand;

import com.yiren.core.Explorer;
import com.yiren.utils.GeneratorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: 基本探索者
 *
 * @author weilin
 * @since 2023-12-27
 */
public abstract class BaseExplorer implements Explorer, Testable, GeneratorFactory, EasyPrintable {

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  @Override
  public Logger getLogger() {
    return log;
  }
}
