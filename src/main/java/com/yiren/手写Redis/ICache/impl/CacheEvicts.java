package com.yiren.手写Redis.ICache.impl;

import com.yiren.手写Redis.ICache.ICacheEvict;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class CacheEvicts {

    /**
     * 工厂模式
     * @return ICacheEvict的实现类
     */
    public static <K, V> ICacheEvict<K, V> fifo() {
        return new CacheEvictFIFO<>();
    }

}
