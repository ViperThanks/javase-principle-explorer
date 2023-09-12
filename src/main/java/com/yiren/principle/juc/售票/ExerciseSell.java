package com.yiren.principle.juc.售票;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 5/9/2023
 */
public class ExerciseSell {


  private static final Logger log = LoggerFactory.getLogger(ExerciseSell.class);
  public static void main(String[] args) {
    TicketWindow window = new TicketWindow(1000);
    List<@NonNull Integer> list = new Vector<>();
    List<@NonNull Thread> threads = new ArrayList<>();

    for (int i = 0; i < 4000; i++) {
      Thread thread = new Thread(() -> {
        int amount = window.sell(randomNumber());
        try {
          Thread.sleep(randomNumber());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        list.add(amount);
      });
      threads.add(thread);
      thread.start();
    }

    threads.forEach(x -> {
      try {
        x.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    int sum = list.stream().mapToInt(i -> i).sum();
    int count = window.getCount();

    log.debug("剩余票数 {} ", count);
    log.debug("卖出的 {} ", sum);

  }

  static Random random = new Random();

  public static int randomNumber() {
    return random.nextInt(5) + 1;
  }



}

class TicketWindow{

  private int count = 0;

  public TicketWindow(int count) {
    this.count = count;
  }

  public int sell(int amount){
    if (this.count >= amount) {
      this.count -= amount;
      return amount;
    }
    return 0;
  }

  public int getCount() {
    return count;
  }
}
