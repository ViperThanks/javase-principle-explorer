package com.yiren.principle.javase.手写Redis.一实现固定缓存大小;

import com.google.common.primitives.Ints;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.yiren.utils.Required.requiredStatus;

/**
 * <p>
 * desc: 基于linked hash map实现的lru map，核心方法是 {@linkplain LinkedHashMap#removeEldestEntry(Map.Entry)}
 * 如果他的条件为 true 就会删除掉最老的 entry
 * 由于是固定大小的 用一个属性 capacity 去固定
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public class LruMap<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LruMap(final int capacity) {
        super(calculateCapacity(capacity), 0.75f, true);
        this.capacity = capacity;
    }

    //    --- core method ---
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public synchronized V getValue(K key) {
        return get(key);
    }

    /**
     * 这里加sync那就是linked hash map 原本v就是线程不安全的
     *
     * @param key
     * @param value
     */
    public synchronized V putValue(K key, V value) {
        return put(key, value);
    }

    public synchronized boolean removeValue(K key) {
        return remove(key) != null;
    }

    public synchronized boolean removeAll() {
        clear();
        return true;
    }

    /**
     * copy guava的
     */
    static int calculateCapacity(int expectedSize) {
        if (expectedSize < 3) {
            requiredStatus(expectedSize > 0, "expectedSize");
            return expectedSize + 1;
        }
        if (expectedSize < Ints.MAX_POWER_OF_TWO) {
            // This is the calculation used in JDK8 to resize when a putAll
            // happens; it seems to be the most conservative calculation we
            // can make.  0.75 is the default load factor.
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }
        return Integer.MAX_VALUE; // any large value
    }
}
