package com.yiren.手写Redis.ICache;

/**
 * <p>
 * desc: 驱逐策略
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
@FunctionalInterface
public interface ICacheEvict<K, V> {


    /**
     * 核心驱逐策略
     */
    public void evict(ICacheEvictContext<K, V> context);
}
