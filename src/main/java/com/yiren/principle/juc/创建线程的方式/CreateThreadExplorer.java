package com.yiren.principle.juc.创建线程的方式;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc:  {@link com.yiren.principle.javase.thread.CreateThread4MethodExplorer}也有
 * </p>
 *
 * @author weilin
 * @since 24/8/2023
 */
public class CreateThreadExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(CreateThreadExplorer.class);

  @Override
  public void explore() throws Exception {
    //method1();
    //method2();
    method3();
  }

  void method1() {
    // 正常的new一个Thread对象
    Thread thread = new Thread("thread1") {
      @Override
      public void run() {
        log.info("method1");
      }
    };
    thread.start();
  }

  void method2() {
    // 通过Runnable接口实现
    Runnable runnable = () -> log.info("method2");
    Thread thread = new Thread(runnable, "thread2");
    thread.start();
  }

  void method3() throws ExecutionException, InterruptedException {
    // 通过futureTask实现
    FutureTask<String> futureTask = new FutureTask<>(() -> {
      log.info("method3");
      TimeUnit.SECONDS.sleep(2);
      return "method3";
    });

    Thread thread = new Thread(futureTask, "thread3");
    thread.start();
    log.debug("{}", futureTask.get());

  }


  public static void main(String[] args) {
    Executor.executeExplorer(CreateThreadExplorer.class);
  }
}
