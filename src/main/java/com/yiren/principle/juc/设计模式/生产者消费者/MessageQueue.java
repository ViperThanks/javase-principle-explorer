package com.yiren.principle.juc.设计模式.生产者消费者;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * <p>
 * desc: 线程间通信的
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class MessageQueue<T> {
  private static final Logger log = LoggerFactory.getLogger(MessageQueue.class);


  private final LinkedList<Message<T>> queue = new LinkedList<>();
  private final int capacity;


  public MessageQueue(int capacity) {
    this.capacity = capacity;
  }

  public Message<T> take() {
    synchronized (queue) {
      while (queue.isEmpty()) {
        try {
          log.debug("队列为空，消费者等待");
          queue.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      Message<T> message = queue.removeFirst();
      log.debug("已消费消息 {} ", message);
      queue.notifyAll();
      return message;
    }
  }


  /**
   * 发送信息
   */
  public void send(Message<T> message) {
    synchronized (queue) {
      //满了
      while (checkCapacity()) {
        //等就完事了
        try {
          log.debug("队列满了，生产者等待");
          queue.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      //已经不满了😀
      queue.addLast(message);
      log.debug("已生产消息 {} ", message);
      queue.notifyAll();
    }
  }

  private boolean checkCapacity() {
    return (queue.size() - capacity >= 0);
  }

}


