package com.yiren.tmp;


import com.yiren.core.Executor;
import com.yiren.principle.javase.classload.LoadClassExplorer;
import com.yiren.principle.javase.thread.CreateThread4MethodExplorer;
import com.yiren.principle.juc.多把锁.MultiLockExplorer;
import com.yiren.utils.performance.core.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class asdfsadf {

private static final Logger LOGGER = LoggerFactory.getLogger(asdfsadf.class);



  public static void main(String[] args) throws Exception {
    Executor.executeExplorer(LoadClassExplorer.class, MultiLockExplorer.class,null);
  }

  @Stopwatch
  public void hello() {
    System.out.println("123");
  }
}



