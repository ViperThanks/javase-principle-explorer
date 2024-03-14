package com.yiren.principle.guava.cache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.hash.MurmurHash;
import com.google.common.hash.Hashing;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.google.common.base.Preconditions.checkArgument;
/**
 * desc: 基于装饰者模式实现的lru
 *
 * @author Viper Thanks
 * @since 24/2/2024
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {
    private final int limit;
    private final InternalLRUCache<K,V> internalLRUCache;
    public LinkedHashLRUCache(int limit) {
        checkArgument(limit > 0 , "limit must greater than 0");
        this.limit = limit;
        internalLRUCache = new InternalLRUCache<>(limit);
    }

    /**
     * 内部lru缓存实现
     */
    private static final class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {
        private static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

        private final int limit;

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }

        private InternalLRUCache(int limit){
            super(capacity(limit));
            this.limit = limit;
        }

        public int getLimit() {
            return limit;
        }

        static int capacity(int expectedSize) {
            if (expectedSize < 3) {
                if (expectedSize < 0) {
                    throw new IllegalArgumentException ("expectedSize cannot be negative but was: " + expectedSize);
                }
                return expectedSize + 1;
            }
            if (expectedSize < MAX_POWER_OF_TWO) {
                return (int) Math.ceil(expectedSize / 0.75);
            }
            return Integer.MAX_VALUE; // any large value
        }
    }


    @Override
    public V get(K key) {
        return this.internalLRUCache.get(key);
    }


    @Override
    public int size() {
        return this.internalLRUCache.size();
    }

    @Override
    public int limit() {
        return limit;
    }


    @Override
    public V remove(K key) {
        return this.internalLRUCache.remove(key);
    }


    @Override
    public V put(K key, V value) {
        return this.internalLRUCache.put(key, value);
    }

    @Override
    public void clear() {
        this.internalLRUCache.clear();
    }

    @Override
    public String toString() {
        return this.internalLRUCache.toString();
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.internalLRUCache.forEach(action);
    }
}
