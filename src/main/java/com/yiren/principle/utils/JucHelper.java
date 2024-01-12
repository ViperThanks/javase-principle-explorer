package com.yiren.principle.utils;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 19/9/2023
 */
public final class JucHelper {

  private JucHelper() {
  }

  private static final Class<Unsafe> UNSAFE_CLASS = Unsafe.class;
  private static final String UNSAFE_FIELD_NAME = "theUnsafe";

  private static volatile Unsafe unsafe;

  /**
   * 获取unsafe
   * 基于double check lock的懒汉单例 + 反射
   *
   * @return unsafe
   */
  @SneakyThrows
  public static Unsafe getUnsafe() {
    if (unsafe == null) {
      synchronized (JucHelper.class) {
        if (unsafe == null) {
          Field unsafeField = UNSAFE_CLASS.getDeclaredField(UNSAFE_FIELD_NAME);
          unsafeField.setAccessible(true);
          unsafe = (Unsafe) unsafeField.get(null);
        }
      }
    }
    return unsafe;
  }


  public static List<Thread> getThreads(int size, Runnable runnable) {
    List<Thread> threads = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      threads.add(new Thread(runnable, "thread-" + i));
    return threads;
  }

  public static void submitTask(int size, Runnable runnable) {
    LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(size);
    AtomicInteger atomicInteger = new AtomicInteger(0);
    ThreadPoolExecutor pool = new ThreadPoolExecutor(size, size,
      0L, TimeUnit.NANOSECONDS,
      queue,
      t -> new Thread(t, "thread " + atomicInteger.getAndIncrement()),
      new ThreadPoolExecutor.AbortPolicy());
    for (int i = 0; i < size; i++) {
      pool.execute(runnable);
    }
  }

  
}
