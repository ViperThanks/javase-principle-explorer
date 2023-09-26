package com.yiren.principle.juc.线程池的种类;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 17/9/2023
 */
public class TypesOfThreadPoolExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(TypesOfThreadPoolExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    Executors.newSingleThreadExecutor();
    Executors.newCachedThreadPool();
    Executors.newWorkStealingPool();
    Future<?> hello = pool.submit(() -> {
      LOGGER.info("hello");
    });

  }

  public static void main(String[] args) {
    Executor.executeExplorer(TypesOfThreadPoolExplorer.class);
  }


}