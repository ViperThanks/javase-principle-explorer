package com.yiren.principle.juc.不可变类型;

import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import com.yiren.principle.utils.JucHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 19/9/2023
 */
public class ImmutableTypesExplorer extends BaseExplorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableTypesExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    dateTime();
    p(SEPARATOR);
    dateTime2();
  }


  /**
   * 因为 SimpleDateFormat 不是线程安全的，所以会出现异常
   */
  private void dateTime() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //for (int i = 0; i < 10; i++) {
    //  new Thread(() -> {
    //    try {
    //      LOGGER.debug("{}", simpleDateFormat.parse("2019-09-19"));
    //    } catch (Exception e) {
    //      LOGGER.debug("error", e);
    //    }
    //  }).start();
    //}

    JucHelper.getThreads(10, () -> {
      try {
        LOGGER.debug("{}", simpleDateFormat.parse("2019-09-19"));
      } catch (Exception e) {
        LOGGER.debug("error", e);
      }
    }).forEach(Thread::start);

  }

  private void dateTime2() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JucHelper.getThreads(10, () -> {
      try {
        LOGGER.debug("{}", dateTimeFormatter.parse("2019-09-19"));
      } catch (Exception e) {
        LOGGER.debug("error", e);
      }
    }).forEach(Thread::start);

  }


  public static void main(String[] args) {
    Executor.executeMyselfWithTime();
  }


}