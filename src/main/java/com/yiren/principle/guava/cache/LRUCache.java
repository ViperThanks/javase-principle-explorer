package com.yiren.principle.guava.cache;

/**
 * desc: LRU 缓存 接口
 *
 * @param <K> key泛型
 * @param <V> value泛型
 * @author Viper Thanks
 * @since 24/2/2024
 */
public interface LRUCache<K, V> {
    //query
    V get(K key);

    int size();

    int limit();

    //update
    V remove(K key);

    V put(K key, V value);

    void clear();
}
