package com.yiren.MyRedis.ICache;

import java.util.Collection;

/**
 * <p>
 * desc: 缓存过期者，存在主动淘汰，被动淘汰
 * </p>
 *
 * @author weilin
 * @since 2023-10-13
 */
public interface ICacheExpire<K, V> {

  /**
   * 指定过期信息
   * @param key key
   * @param expireAt 什么时候过期
   * @since 0.0.3
   */
  void expire(final K key, final long expireAt);

  /**
   * 惰性删除中需要处理的 keys
   * @param keyList keys
   * @since 0.0.3
   */
  void refreshExpire(final Collection<K> keyList);

}
