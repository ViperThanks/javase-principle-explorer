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

  /** v2新加的方法
   * @param timeout 最多等多久
   * @return 结果
   */
  public T get(long timeout) {

    synchronized (this) {
      long base = System.currentTimeMillis();
      long passedTime = 0;
      while (response == null) {
        long waitTime = timeout - passedTime;
        if (waitTime <= 0) {
          break;
        }

        try {
          //思考这里为什么？ 答案：虚假唤醒
          this.wait(waitTime);

        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        //record time
        passedTime = System.currentTimeMillis() - base;
      }
      return response;
    }
  }

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
