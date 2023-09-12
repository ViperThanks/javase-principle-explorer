package com.yiren.principle.juc.静态模型;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc: 静态条件 临界区代码
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class RaceConditionExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(RaceConditionExplorer.class);

  static int counter = 0;

  static AtomicInteger atomicCounter = new AtomicInteger(0);

  static final Object objectLock = new Object();

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {

    raceCondition();
    atomicCounter();
    synchronizedApproach();
  }

  public static void raceCondition() throws InterruptedException {
    counter = 0;
    Thread incr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区 对一个变量进行读写操作
        // counter class code
        /*
        0: getstatic     #2                  // Field counter:I  读操作
        3: iconst_1
        4: iadd
        5: putstatic     #2                  // Field counter:I  写操作
        */
        counter++;
      }
    }, "incr");
    Thread decr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区
        counter--;
      }
    }, "decr");


    incr.start();
    decr.start();

    incr.join();
    decr.join();
    log.info("counter :  {} ", counter);
  }


  public static void atomicCounter() throws InterruptedException {
    Thread incr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区 对一个变量进行读写操作
        atomicCounter.getAndIncrement();
      }
    }, "incr");
    Thread decr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区
        atomicCounter.getAndDecrement();
      }
    }, "decr");


    incr.start();
    decr.start();

    incr.join();
    decr.join();
    log.info("atomicCounter :  {} ", atomicCounter);
  }

  public static void synchronizedApproach() throws InterruptedException {
    counter = 0;
    Thread incr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区 对一个变量进行读写操作
        // counter class code
        /*
        0: getstatic     #2                  // Field counter:I  读操作
        3: iconst_1
        4: iadd
        5: putstatic     #2                  // Field counter:I  写操作
        */
        synchronized (objectLock) {
          counter++;
        }
      }
    }, "incr");
    Thread decr = new Thread(() -> {
      for (int i = 0; i < 500000; i++) {
        //临界区
        synchronized (objectLock) {
          counter--;
        }
      }
    }, "decr");


    incr.start();
    decr.start();

    incr.join();
    decr.join();
    log.info("sync counter :  {} ", counter);
  }

  public static void synchronizedApproach2() throws InterruptedException {
    counter = 0;
    Thread incr = new Thread(() -> {
      synchronized (objectLock) {
        for (int i = 0; i < 500000; i++) {
          //临界区 对一个变量进行读写操作
          counter++;
        }
      }
    }, "incr");
    Thread decr = new Thread(() -> {
      synchronized (objectLock) {
        for (int i = 0; i < 500000; i++) {
          //临界区
          counter--;
        }
      }
    }, "decr");


    incr.start();
    decr.start();

    incr.join();
    decr.join();
    log.info("sync counter :  {} ", counter);
  }

  public static void main(String[] args) {
    Executor.executeExplorer(RaceConditionExplorer.class);
  }


}