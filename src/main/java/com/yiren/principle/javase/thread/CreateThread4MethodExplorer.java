package com.yiren.principle.javase.thread;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */
public class CreateThread4MethodExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(CreateThread4MethodExplorer.class);

  @Override
  public void explore() {
    LinkedList<Integer> linkedList = new LinkedList<>();
    linkedList.add(1);
    linkedList.add(2);
    System.out.println(linkedList);
    //方法一 继承Thread类
    log.info("方法一 继承Thread类");
    new A().start();
    //方法二 实现Runnable接口
    log.info("方法二 实现Runnable接口");
    new Thread(new B()).start();
    //方法三 实现Callable接口
    log.info("方法三 实现Callable接口");
    Callable<String> callable = new C();
    FutureTask<String> futureTask = new FutureTask<>(callable);
    //方法四 executors
    log.info("方法四 executors");
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.execute(new A());
  }

  private static class A extends Thread {
    @Override
    public void run() {
      System.out.println("A");
    }
  }

  private static class B implements Runnable {
    @Override
    public void run() {
      System.out.println("B");
    }
  }

  private static class C implements Callable<String> {

    @Override
    public String call() throws Exception {
      return null;
    }
  }

  public static void main(String[] args) {
    Executor.executeExplorer(CreateThread4MethodExplorer.class);
  }
}


