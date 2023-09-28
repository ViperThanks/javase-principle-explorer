package com.yiren.手写Redis.ICache.impl;

import com.yiren.手写Redis.ICache.ICache;
import com.yiren.手写Redis.ICache.ICacheEvict;
import com.yiren.手写Redis.ICache.ICacheEvictContext;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * desc: 注意oom，因为代码不是我的，等等研究一下能不能初始容量，如果可以arraydeque或许是个不错的选择
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class CacheEvictFIFO<K, V> implements ICacheEvict<K, V> {

    private final Queue<K> queue = new LinkedList<>();

    @Override
    public void evict(ICacheEvictContext<K, V> context) {
        final ICache<K, V> cache = context.cache();
        //超过限制，
        if (cache.size() >= context.size()) {
            final K evictKey = queue.remove();
            cache.remove(evictKey);
        }
        final K key = context.key();
        queue.add(key);
    }
}

