package com.yiren.principle.juc.偏向锁;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 偏向锁实验   还有wait notify 会影响锁的状态，因为只有重量级锁才会有wait notify
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class BiasedLockExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(BiasedLockExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    Dog d = new Dog();
    //偏向锁
    log.info("1. {} ", ClassLayout.parseInstance(d).toPrintable());
    //加锁
    synchronized (d) {
      //偏向锁 + 线程ID
      log.info("2. {} ", ClassLayout.parseInstance(d).toPrintable());
    }
    //偏向锁 + 线程ID
    log.info("3. {} ", ClassLayout.parseInstance(d).toPrintable());
    Thread t1 = new Thread(() -> {
      synchronized (d) {
        //偏向锁 -> 轻量级锁
        log.info("4. {} ", ClassLayout.parseInstance(d).toPrintable());
      }
    }, "t1");
    t1.start();

    Thread t2 = new Thread(() -> {
      synchronized (d) {
        //轻量级锁 -> 重量级锁
        log.info("5. {} ", ClassLayout.parseInstance(d).toPrintable());
      }
    }, "t2");
    t2.start();


    t1.join();
    t2.join();
    TimeUnit.SECONDS.sleep(5);
    log.info("6. {} ", ClassLayout.parseInstance(d).toPrintable());
    log.info(String.valueOf(Dog.class.hashCode()));
  }

  //add vm option -XX:BiasedLockingStartupDelay=0
  public static void main(String[] args) {
    Executor.executeExplorer(BiasedLockExplorer.class);
  }

}

class Dog {

}