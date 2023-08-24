package com.yiren.principle.javase.object;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.utils.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * desc  : 看一下一堆log对象占用多少字节
 * author: weilin
 * date  : 20/7/2023
 */
public class LogRecordBytesDetailExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(LogRecordBytesDetailExplorer.class);


  @Override
  public void explore() {
    final int times = 10000;
    List<LogRecord> logRecords = new ArrayList<>(times);
    Performance.run(() -> {

      log.info("start to create {} logRecord", times);
      for (int i = 0; i < times; i++) {
        LogRecord logRecord = new LogRecord()
          .setBusinessName("businessName" + i)
          .setIp("ip" + i)
          .setMethod("method" + i)
          .setMethodName("methodName" + i)
          .setOperateTime(LocalDateTime.now())
          .setUrl("url" + i);
        logRecords.add(logRecord);
      }
      log.info("finish");
      log.info("log records size ： {}", logRecords.size());

    }).toPrintable();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(LogRecordBytesDetailExplorer.class);
  }

}




