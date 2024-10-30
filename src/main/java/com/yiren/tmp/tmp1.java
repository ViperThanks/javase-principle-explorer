package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.expand.ExplorerTestable;
import com.yiren.entity.User;
import com.yiren.utils.CommonUtils;
import java.util.ArrayList;
import java.util.LongSummaryStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-11-22
 */
public class tmp1 implements ExplorerTestable {

  private static final Logger LOGGER = LoggerFactory.getLogger(tmp1.class);


  public static void main(String[] args) {
    Executor.executeExplorer(tmp1.class);
  }

  /**
   * 探索 方法
   */
  @Override
  public void explore() throws Exception {

    int times = 100_000;
    ArrayList<Long> testUserList = new ArrayList<>(times);
    ArrayList<Long> newUserList = new ArrayList<>(times);
    for (int i = 0; i < times; i++) {
      testUserList.add(CommonUtils.calculateNanoseconds(this::getTestUser));
      newUserList.add(CommonUtils.calculateNanoseconds(User::new));
    }
    LongSummaryStatistics longSummaryStatistics = new LongSummaryStatistics();

    LOGGER.info("test user cost time {} , new user cost time {} ",
        testUserList.stream().mapToLong(Long::longValue).average().orElse(0),
        newUserList.stream().mapToLong(Long::longValue).average().orElse(0));

  }
}
