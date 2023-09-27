package com.yiren.principle.javase.手写Redis.ICache.impl;

import com.yiren.principle.javase.手写Redis.ICache.ICache;
import com.yiren.principle.javase.手写Redis.ICache.ICacheEvict;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class Cache<K, V> implements ICache<K, V> {

    private Map<K, V> map;

    private int sizeLimit;

    private ICacheEvict<K, V> cacheEvict;


    @Override
    public V put(K key, V value) {
        CacheEvictContext<K, V> ctx = new CacheEvictContext<>();
        ctx.key(key).size(sizeLimit).cache(this);
        cacheEvict.evict(ctx);
        if (isSizeLimit()) {
            throw new RuntimeException("当前队列已满，数据添加失败！");
        }
        return map.put(key,value);
    }
    private boolean isSizeLimit() {
        final int currentSize = this.size();
        return currentSize >= this.sizeLimit;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
