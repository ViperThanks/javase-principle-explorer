package com.yiren.MyRedis.ICache;

import java.util.Map;

public interface ICache<K, V> extends Map<K, V> {

  /**
   * 让 key 在 timeIn Millis 的 毫秒值 后 失效
   *
   * @param key          key的名字
   * @param timeInMillis 过期的毫秒值
   * @return this
   */
  ICache<K, V> expire(final K key, final long timeInMillis);

  /**
   * 在这个时间戳过期
   *
   * @param key          key的名字
   * @param timeImMillis 过期时间的毫秒值，如2023-10-13 15:12:03 —> 1697181123000
   * @return this
   */
  ICache<K, V> expireAt(final K key, final long timeImMillis);
}