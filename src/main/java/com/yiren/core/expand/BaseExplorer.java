package com.yiren.core.expand;

import com.yiren.core.Explorer;
import com.yiren.core.Testable;
import com.yiren.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-27
 */
public abstract class BaseExplorer extends CommonUtils implements Explorer, Testable {

  public final Logger LOGGER = LoggerFactory.getLogger(getClass());

}
