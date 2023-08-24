package com.yiren.principle.jvm.jmm.可见性;

import com.yiren.core.Explorer;
import com.yiren.core.Executor;

/**
 * desc  :
 * author: weilin
 * date  : 17/8/2023
 */
public class VolatileExplorer implements Explorer {


  static boolean flag = true;

  @Override
  public void explore() throws Exception {
    Thread t1 = new Thread(() -> {
      while (flag) {
        //如果加了sync也会
        //synchronized (this){
        //
        //}
      }
    });
    t1.start();

    Thread.sleep(1000L);
    flag = false;
  }


  public static void main(String[] args) {
    Executor.executeExplorer(VolatileExplorer.class);
  }
}
