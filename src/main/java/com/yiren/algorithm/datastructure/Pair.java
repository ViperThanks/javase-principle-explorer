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
    private K first;
    private V second;

    public Pair() {
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    /**
     * 转为字符串 like (1,2) (2,3) (3,4)
     *
     * @return  (1,2)
     */
    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
