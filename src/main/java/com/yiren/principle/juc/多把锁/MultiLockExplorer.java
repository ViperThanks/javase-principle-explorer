package com.yiren.principle.juc.多把锁;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 13/9/2023
 */
public class MultiLockExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(MultiLockExplorer.class);

  @Override
  public void explore() throws Exception {
    BigRoom bigRoom = new BigRoom();

  }

  public static void main(String[] args) {
    Executor.executeExplorer(MultiLockExplorer.class);
  }
}

class BigRoom{
  private static final Logger log = LoggerFactory.getLogger(BigRoom.class);

  private static final Object bedroom = new Object();
  private static final Object studyRoom = new Object();

  public void sleep(){
    synchronized (bedroom){
      log.info("sleeping in bedroom");
    }
  }

  public void study(){
    synchronized (studyRoom){
      log.info("study in studyRoom");
    }
  }
}
