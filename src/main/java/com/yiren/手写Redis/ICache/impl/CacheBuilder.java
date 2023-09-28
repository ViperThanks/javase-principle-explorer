package com.yiren.手写Redis.ICache.impl;

import com.yiren.手写Redis.ICache.ICache;
import com.yiren.手写Redis.ICache.ICacheEvict;

import java.util.HashMap;
import java.util.Map;

import static com.yiren.utils.Required.requirePositive;
import static com.yiren.utils.Required.requiredStatus;

/**
 * 缓存引导类 * @author binbin.hou * @since 0.0.2
 */
public final class CacheBuilder<K, V> {
    private CacheBuilder() {
    }

    /**
     * 创建对象实例
     *  @param <K> key
     *  @param <V> value
     *  @return this * @since 0.0.2
     */
    public static <K, V> CacheBuilder<K, V> builder() {
        return new CacheBuilder<>();
    }

    /**
     * map 实现
     * @since 0.0.2
     */
    private Map<K, V> map = new HashMap<>();
    /**
     * 大小限制
     * @since 0.0.2
     */
    private int size = Integer.MAX_VALUE;
    /**
     * 驱除策略
     * @since 0.0.2
     */
    private ICacheEvict<K, V> evict = CacheEvicts.fifo();

    /**
     * map 实现 *
     * @param map map *
     * @return this * @since 0.0.2
     */
    public CacheBuilder<K, V> map(Map<K, V> map) {
        requiredStatus(map != null);
        this.map = map;
        return this;
    }

    /**
     * 设置 size 信息 *
     * @param size size *
     * @return this *
     * @since 0.0.2
     */
    public CacheBuilder<K, V> size(int size) {
        requirePositive(size, "size");
        this.size = size;
        return this;
    }

    /**
     * 设置驱除策略 *
     * @param evict 驱除策略 *
     * @return this *
     * @since 0.0.2
     */
    public CacheBuilder<K, V> evict(ICacheEvict<K, V> evict) {
        this.evict = evict;
        return this;
    }

    /**
     * 构建缓存信息 *
     * @return 缓存信息 *
     * @since 0.0.2
     */
    public ICache<K, V> build() {
        CacheContext<K, V> context = new CacheContext<>();
        context.cacheEvict(evict);
        context.map(map);
        context.size(size);
        return new Cache<>(context);
    }
}