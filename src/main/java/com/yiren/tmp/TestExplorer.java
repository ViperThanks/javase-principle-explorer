package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.juc.静态模型.RaceConditionExplorer;
import com.yiren.utils.Performance;
import com.yiren.utils.StopWatch;
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
public class TestExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(TestExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Performance.run(() -> {
      try {
        RaceConditionExplorer.synchronizedApproach();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).toPrintable();

    long l = StopWatch.calculateMilliseconds(() -> {
      try {
        RaceConditionExplorer.synchronizedApproach();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    log.info("l:{}", l);

    Performance.run(() -> {
      try {
        RaceConditionExplorer.synchronizedApproach2();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).toPrintable();

    long v = StopWatch.calculateMilliseconds(() -> {
      try {
        RaceConditionExplorer.synchronizedApproach2();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    log.info("v:{}", v);

    Performance.run(() -> {
      try {
        RaceConditionExplorer.atomicCounter();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).toPrintable();
    Performance.run(() -> {
      try {
        RaceConditionExplorer.raceCondition();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).toPrintable();


  }

  public static void main(String[] args) {
    Executor.executeExplorer(TestExplorer.class);
  }


}