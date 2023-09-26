package com.yiren.principle.juc.Unsafe;

import com.yiren.principle.utils.PrincipleUtil;


/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class MyAtomicInteger extends Number implements java.io.Serializable {
  private volatile int value;
  private static final sun.misc.Unsafe unsafe;
  private static final long offset;

  static {
    unsafe = PrincipleUtil.getUnsafe();
    try {
      offset = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }

  public MyAtomicInteger() {
  }

  public MyAtomicInteger(int value) {
    this.value = value;
  }

  public int get() {
    return value;
  }

  public boolean compareAndSet(int expect, int update) {
    return unsafe.compareAndSwapObject(this, offset, expect, update);
  }

  public int getAndAdd(int delta) {
    int prev, next;
    do {
      prev = value;
      next = prev + delta;
    } while (!unsafe.compareAndSwapInt(this, offset, prev, next));
    return prev;
  }

  public int addAndGet(int delta) {
    int prev, next;
    do {
      prev = value;
      next = prev + delta;
    } while (!unsafe.compareAndSwapInt(this, offset, prev, next));
    return next;
  }

  public int getAndIncrement() {
    return getAndAdd(1);
  }

  public int incrementAndGet() {
    return addAndGet(1);
  }

  public int getAndDecrement() {
    return getAndAdd(-1);
  }

  public int decrementAndGet() {
    return addAndGet(-1);
  }


  @Override
  public int intValue() {
    return value;
  }

  @Override
  public long longValue() {
    return (long) value;
  }

  @Override
  public float floatValue() {
    return (float) value;
  }

  @Override
  public double doubleValue() {
    return (double) value;
  }
}
