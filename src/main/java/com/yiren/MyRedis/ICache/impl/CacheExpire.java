package com.yiren.MyRedis.ICache.impl;

import com.yiren.MyRedis.ICache.ICache;
import com.yiren.MyRedis.ICache.ICacheExpire;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 2023-10-13
 */
public class CacheExpire<K, V> implements ICacheExpire<K, V> {

  private final Map<K, Long> expireMap = new HashMap<>();

  private static final int LIMIT = 100;

  private final ICache<K, V> cache;

  private static final ScheduledExecutorService EXPIRED_CLEANER = Executors.newSingleThreadScheduledExecutor();


  public CacheExpire(ICache<K, V> cache) {
    this.cache = cache;
    this.init();
  }

  private void init() {
    EXPIRED_CLEANER.scheduleAtFixedRate(new ExpireThread(), 100, 100, TimeUnit.MILLISECONDS);
  }

  @Override
  public void expire(K key, long expireAt) {
    expireMap.put(key, expireAt);
  }

  @Override
  public void refreshExpire(Collection<K> keyList) {

  }

  /**
   * 执行过期操作
   *
   * @param entry 明细
   * @since 0.0.3
   */
  private void expireKey(Map.Entry<K, Long> entry) {
    final K key = entry.getKey();
    final Long expireAt = entry.getValue();
    // 删除的逻辑处理
    long currentTime = System.currentTimeMillis();
    if (currentTime >= expireAt) {
      expireMap.remove(key);
      // 再移除缓存，后续可以通过惰性删除做补偿
      cache.remove(key);
    }
  }

  private class ExpireThread implements Runnable {

    @Override
    public void run() {
      //1.判断是否为空
      if (expireMap.isEmpty()) {
        return;
      }
      //2. 获取 key 进行处理
      int count = 0;
      for (Map.Entry<K, Long> entry : expireMap.entrySet()) {
        if (count >= LIMIT) {
          return;
        }
        expireKey(entry);
        count++;
      }
    }
  }
}

