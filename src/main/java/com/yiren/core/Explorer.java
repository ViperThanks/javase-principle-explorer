package com.yiren.core;

/**
 * desc  : 核心接口探索者
 * author: wl
 * date  : 17/6/2023 下午 1:51
 * email : vieper0714@outlook.com
 */
@FunctionalInterface
public interface Explorer {

  String TAG = "Explorer";

  /**
   * 探索 方法
   * @throws Exception e
   */
  void explore() throws Exception;
}
