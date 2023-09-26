package com.yiren.principle.juc.设计模式.生产者消费者;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class CustomerPublisherExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(CustomerPublisherExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    MessageQueue<String> queue = new MessageQueue<>(2);
    for (int i = 0; i < 3; i++) {
      final int id = i;
      new Thread(() -> {
        queue.send(new Message<>(id, "hello world"));
      }, "生产者" + i).start();
    }

    TimeUnit.SECONDS.sleep(3);


    new Thread(() -> {
      while (true) {
        Message<String> take = queue.take();
        //pretend bus time
        try {
          TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }, "消费者").start();


  }

  public static void main(String[] args) {
    Executor.executeExplorer(CustomerPublisherExplorer.class);
  }


}