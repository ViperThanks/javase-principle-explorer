package com.yiren.tmp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.eventbus.EventBus;
import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.expand.BaseTest;
import com.yiren.entity.User;
import java.util.Arrays;
import java.util.List;

/**
 * desc:
 *
 * @author weilin
 * @since 2024-1-2
 */
public class tmp111 extends BaseTest implements Explorer {

  public static void main(String[] args) {
    Executor.executeExplorer(tmp111.class);
  }

  private static final int logicNull = Integer.MIN_VALUE;
  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    EventBus eventBus = new EventBus();
    User[] users = toArray(getTestUser(), getTestUser(), getTestUser());
    List<User> list = Arrays.asList(getTestUser(), getTestUser(), getTestUser());
    LOGGER.info(JSON.toJSONString(list, true));

  }
}
