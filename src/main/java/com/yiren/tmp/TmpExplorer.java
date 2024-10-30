package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.ExplorerTestable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-8
 */
public class TmpExplorer implements ExplorerTestable {

  private static final Logger LOGGER = LoggerFactory.getLogger(TmpExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {

  }

  public static void main(String[] args) {
    Executor.executeExplorer(TmpExplorer.class);
  }
}



enum Python {
  RANGE(1, "range"), List(1, "list"),
  ;


  private final int index;
  private final String description;

  Python(int index, String description) {
    this.index = index;
    this.description = description;
  }
}

class VO {
  private String sendDate;

  private Long companyId;

  public String getSendDate() {
    return sendDate;
  }

  public VO setSendDate(String sendDate) {
    this.sendDate = sendDate;
    return this;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public VO setCompanyId(Long companyId) {
    this.companyId = companyId;
    return this;
  }
}
