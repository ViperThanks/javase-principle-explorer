package com.yiren.tmp;

import com.google.common.collect.ImmutableMap;
import com.yiren.principle.juc.Unsafe.MyAtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class TestWhy {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestWhy.class);

  public static void main(String[] args) {

    MyAtomicInteger myAtomicInteger = new MyAtomicInteger(0);
    int i = myAtomicInteger.getAndAdd(5);
    LOGGER.debug(String.valueOf(i));
    LOGGER.debug(String.valueOf(myAtomicInteger.get()));


    AtomicInteger integer = new AtomicInteger(0);

  }
}
