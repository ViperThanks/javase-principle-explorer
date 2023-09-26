package com.yiren.principle.juc.reentrantlock;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 14/9/2023
 */
public class ReentrantLockSummaryExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockSummaryExplorer.class);

  /**
   * 探索 方法
   * reentrant lock 比 synchronized 的区别
   *
   * @throws Exception
   */
  static final ReentrantLock lock = new ReentrantLock();
  @Override
  public void explore() throws Exception {
    //1.可中断性
    lock.lockInterruptibly();
    //2.公平性
    ReentrantLock lock1 = new ReentrantLock(true);
    //3.超时释放 try lock 也有 中断性
    lock1.tryLock();
    lock1.tryLock(100, TimeUnit.SECONDS);
    //4.多个wait set 对比sync只有一个waitset (调用wait方法)
    Condition condition = lock1.newCondition();
    //类似于wait方法，会释放锁，然后到condition消息
    condition.await();
    //类似于notify
    condition.signal();
    //类似于notify all
    condition.signalAll();
  }

  public static void main(String[] args) {
    Executor.executeExplorer(ReentrantLockSummaryExplorer.class);
  }


}