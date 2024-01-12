package com.yiren.principle.javase.time;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yiren.core.Executor;
import com.yiren.core.expand.BaseExplorer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-27
 */
public class ZoneExplorer extends BaseExplorer {

  public static void main(String[] args) {
    Executor.executeExplorer(ZoneExplorer.class);
  }

  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    //获取系统默认时区
    ZoneId zoneId = ZoneId.systemDefault();
    log.info(String.valueOf(zoneId));
    //获取Java所有的时区
    Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
    log.info(JSON.toJSONString(availableZoneIds, SerializerFeature.PrettyFormat));
    //封装对象
    ZoneId zoneId1 = ZoneId.of("America/Cuiaba");
    ZonedDateTime now = ZonedDateTime.now(zoneId1);

  }
}
