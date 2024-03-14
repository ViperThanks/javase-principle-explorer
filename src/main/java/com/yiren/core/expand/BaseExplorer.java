package com.yiren.core.expand;

import com.yiren.core.Explorer;
import com.yiren.core.Testable;
import com.yiren.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: 基本探索者
 *
 * @author weilin
 * @since 2023-12-27
 */
public abstract class BaseExplorer implements Explorer, Testable {

  protected static final String SEPARATOR = "=============================================";

  protected final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
  protected void print(Object o) {
    log.info(CommonUtils.toString(o));
  }
  protected void printWithSeparator(Object o) {
    print(SEPARATOR);
    print(o);
    print(SEPARATOR);
  }

}
