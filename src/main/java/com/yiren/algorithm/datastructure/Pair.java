package com.yiren.algorithm.datastructure;

import java.util.Objects;

/**
 * <p>
 * desc    : 二元组
 * </p>
 *
 * @author weilin
 * @since 30/8/2023
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair() {
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    /**
     * 转为字符串 like (1,2) (2,3) (3,4)
     *
     * @return  (1,2)
     */
    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
