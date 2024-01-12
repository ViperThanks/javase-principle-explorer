package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.ExplorerTestable;
import com.yiren.utils.CommonUtils;
import com.yiren.utils.CommonUtils.RandomHolder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class TestExplorer implements ExplorerTestable {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestExplorer.class);


  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Long[] randomArr = CommonUtils.getRandomArr(100, RandomHolder.THREAD_LOCAL_RANDOM::nextLong);
    LOGGER.info("{}", Arrays.toString(randomArr));

  }
  //n 100 0000
// 1 2

  public static void main(String[] args) {
    Executor.executeExplorer(TestExplorer.class);

  }


}

enum ComparisonType {
  DAILY_SEQUENTIAL,
  MONTHLY_SEQUENTIAL,
  YEAR_OVER_YEAR
}


class Calculator {

  private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");


  public static BigDecimal calculateGrowthRate(BigDecimal before, BigDecimal after) {
    if (before == null || after == null) {
      throw new IllegalArgumentException("Values for calculation cannot be null");
    }
    if (before.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    BigDecimal delta = after.subtract(before);

    return delta
        .divide(before, 4, RoundingMode.HALF_UP)
        .multiply(ONE_HUNDRED);
  }
}