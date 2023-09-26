package com.yiren.principle.juc.reentrantlock;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public class ReentranLockExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReentranLockExplorer.class);

  private static final ReentrantLock lock = new ReentrantLock();


  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    lock.lock();
    try {
      m1();
    }finally {
      lock.unlock();
    }

  }

  private void m1(){
    lock.lock();
    try {
      m2();
      LOGGER.info("enter m1()");
    }finally {
      lock.unlock();
    }
  }

  private void m2() {
    lock.lock();
    try {
      LOGGER.info("enter m2()");
    }finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    Executor.executeExplorer(ReentranLockExplorer.class);
  }


}