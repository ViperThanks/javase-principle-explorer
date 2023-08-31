package com.yiren.core;

/**
 * desc  :
 * author: wl
 * date  : 17/6/2023 下午 1:51
 * email : vieper0714@outlook.com
 */
@FunctionalInterface
public interface Explorer {

  String TAG = "Explorer";

  /**
   * 探索 方法
   * @throws Exception
   */
  void explore() throws Exception;
}
