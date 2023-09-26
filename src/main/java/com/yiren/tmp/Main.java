package com.yiren.tmp;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.yiren.algorithm.datastructure.ListNode;
import com.yiren.algorithm.utils.AlgoUtils;
import com.yiren.design_patterns.单例模式.LazySingleton;
import com.yiren.entity.User;
import com.yiren.utils.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * desc  :
 * author: wl
 * date  : 17/6/2023 下午 1:39
 * email : vieper0714@outlook.com
 */

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


  private static final ReentrantLock lock = new ReentrantLock();


  private static final double one_price = 6;
  private static final double first_discount = 0.8;
  private static final int first_discount_threshold = 80;
  private static final double second_discount = 0.5;
  private static final int second_discount_threshold = 200;
  private static final int day_of_one_month = 25;
  private static final int random_day = (int) (new Random().nextInt(2) + 1);// (0,1] + 1 (1,2]

  public static void main(String[] args) {
    boolean open_first = false, open_second = false;
    double cost_money = one_price, count = 0;
    for (int i = 1; i <= (day_of_one_month - random_day) * 2; i++) {
      count += cost_money;
      //这里可以优化，由于短路与 的特性，当第一次打折后，就不会再进入if判断了

      if (!open_first && count >= first_discount_threshold) {
        LOGGER.info("第{}次开启打8折", i);
        cost_money = one_price * first_discount;
        open_first = true;
      }
      if (!open_second && count >= second_discount_threshold) {
        LOGGER.info("第{}次开启打5折", i);
        cost_money = one_price * second_discount;
        open_second = true;
      }
    }
    LOGGER.info("总共花费{}元", count);

    LOGGER.info("之前的花费");
    //超过15次打6折
    count = 0;
    cost_money = one_price;
    boolean is_open = false;
    for (int i = 1; i <= (day_of_one_month - random_day) * 2; i++) {
      count += cost_money;
      if (!is_open && i > 15) {
        LOGGER.info("第{}次开启打6折", i);
        cost_money = one_price * 0.6;
        is_open = true;
      }
    }
    LOGGER.info("总共花费{}元", count);
  }

  public void changeRef() {
    main(null);
  }


}
