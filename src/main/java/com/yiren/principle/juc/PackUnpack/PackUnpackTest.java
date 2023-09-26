package com.yiren.principle.juc.PackUnpack;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 12/9/2023
 */
public class PackUnpackTest implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(PackUnpackTest.class);

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    //比较神奇的地方
    Thread t1 = new Thread(() -> {
      log.debug("start ....");

      Sleeper.sleep(2);

      log.debug("park ....");
      //如果干粮充足，就不会阻塞 但是如果干粮不足，就会阻塞
      LockSupport.park();

      log.debug("resume ...");
    }, "t1");
    t1.start();

    Sleeper.sleep(1);

    log.debug("unpark ...");
    //补充1次干粮，上限也是1次
    LockSupport.unpark(t1);
  }

  public static void main(String[] args) {
    Executor.executeExplorer(PackUnpackTest.class);
  }


}