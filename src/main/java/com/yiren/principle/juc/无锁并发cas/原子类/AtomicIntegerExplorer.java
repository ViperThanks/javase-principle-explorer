package com.yiren.principle.juc.无锁并发cas.原子类;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class AtomicIntegerExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(AtomicIntegerExplorer.class);

  private static final AtomicInteger i = new AtomicInteger(5);


  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    addAndUpdate(i,x -> x * 2);
    System.out.println(i.get());

  }


  private void addAndUpdate(AtomicInteger i,IntUnaryOperator function) {
    boolean flag;
    do{
      int expect = i.get();
      int i1 = function.applyAsInt(expect);
      flag = i.compareAndSet(expect, i1);
    }while (!flag);
  }

  public static void main(String[] args) {
    Executor.executeExplorer(AtomicIntegerExplorer.class);
  }


}