package com.yiren.principle.juc.售票;

import com.yiren.tmp.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class ExerciseBalanceTransfer {

  private static final Logger log = LoggerFactory.getLogger(ExerciseBalanceTransfer.class);

  public static void main(String[] args) throws InterruptedException {
    Account a = new Account(1000);
    Account b = new Account(1000);
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        a.transfer(b, randomNumber());
      }
    }, "t1");
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        b.transfer(a, randomNumber());
      }
    }, "t2");
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    //
    log.debug("total : {}", (a.getMoney() + b.getMoney()));
  }


  static Random random = new Random();

  public static int randomNumber() {
    return random.nextInt(100) + 1;
  }


}

class Account {

  private int money;

  private static final Object lock = new Object();

  public Account(int money) {
    this.money = money;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  //转账
  public void transfer(Account targetAccount, int amount) {
    synchronized (lock) {
      if (this.money >= amount) {
        this.setMoney(this.getMoney() - amount);
        targetAccount.setMoney(targetAccount.getMoney() + amount);
      }
    }
  }
}
