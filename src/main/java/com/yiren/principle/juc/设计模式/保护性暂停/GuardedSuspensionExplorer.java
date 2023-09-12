package com.yiren.principle.juc.设计模式.保护性暂停;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class GuardedSuspensionExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(GuardedSuspensionExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    GuardedObject<List<String>> future = new GuardedObject<>();
    new Thread(() -> {
      log.debug("等待结果");
      List<String> list = future.get();
      log.info(" i get it {}", list.toString());
    }, "t1").start();

    new Thread(() -> {
      try {
        log.debug("正在下载");
        List<String> download = Downloader.download();
        future.complete(download);
        log.info("download succeed and complete");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }, "t2").start();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(GuardedSuspensionExplorer.class);
  }


}