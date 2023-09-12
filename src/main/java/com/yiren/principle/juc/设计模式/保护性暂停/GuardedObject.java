package com.yiren.principle.juc.设计模式.保护性暂停;

/**
 * <p>
 * desc: 保护性暂停 中间类 ，主要作用是协调两个线程
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class GuardedObject<T> {

  private T response;

  public T get() {
    synchronized (this) {
      while (response == null) {
        try {
          this.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      return response;
    }
  }


  public void complete(T object) {
    synchronized (this) {
      this.response = object;
      this.notifyAll();
    }
  }

}
