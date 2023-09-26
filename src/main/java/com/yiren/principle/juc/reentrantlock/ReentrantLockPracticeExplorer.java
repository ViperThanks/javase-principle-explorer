package com.yiren.principle.juc.reentrantlock;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ReentrantLockPracticeExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockPracticeExplorer.class);


  static final ReentrantLock ROOM = new ReentrantLock();
  static boolean hasCigarette = false;
  static boolean hasTakeout = false;

  static final Condition waitCigaretteSet = ROOM.newCondition();
  static final Condition waitTakeoutSet = ROOM.newCondition();

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    new Thread(() -> {
      ROOM.lock();
      try {
        LOGGER.debug("烟到了吗？[{}]", hasCigarette);
        while (!hasCigarette) {
          LOGGER.debug("{} 等呗。。。", Thread.currentThread().getName());
          try {
            waitCigaretteSet.await();
          } catch (InterruptedException e) {
            LOGGER.debug("打断我干嘛");
            e.printStackTrace();
          }
        }
        LOGGER.debug("似乎被叫醒了");
        LOGGER.debug("烟到了吗？[{}]", hasCigarette);
        LOGGER.debug("{}干活", Thread.currentThread().getName());
      } finally {
        ROOM.unlock();
      }
    }, "小南").start();


    new Thread(() -> {
      ROOM.lock();
      try {
        LOGGER.debug("外卖到了吗？[{}]", hasTakeout);
        while (!hasTakeout) {
          LOGGER.debug("{} 等呗。。。", Thread.currentThread().getName());
          try {
            waitTakeoutSet.await();
          } catch (InterruptedException e) {
            LOGGER.debug("打断我干嘛");
            e.printStackTrace();
          }
        }
        LOGGER.debug("似乎被叫醒了");
        LOGGER.debug("外卖到了吗？[{}]", hasTakeout);
        LOGGER.debug("{}干活", Thread.currentThread().getName());

      } finally {
        ROOM.unlock();
      }
    }, "小女").start();


    Sleeper.sleep();

    new Thread(() -> {
      ROOM.lock();
      try {
        hasCigarette = true;
        waitCigaretteSet.signal();
      } finally {
        ROOM.unlock();
      }
    }, "送烟小哥").start();

    Sleeper.sleep();

    new Thread(() -> {
      ROOM.lock();
      try {
        hasTakeout = true;
        waitTakeoutSet.signal();
      } finally {
        ROOM.unlock();
      }
    }, "外卖小哥").start();

  }


  public static void main(String[] args) {
    Executor.executeExplorer(ReentrantLockPracticeExplorer.class);
  }


}