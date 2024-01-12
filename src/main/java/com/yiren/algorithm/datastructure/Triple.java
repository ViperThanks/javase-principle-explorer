package com.yiren.algorithm.datastructure;

import java.util.Objects;

/**
 * <p>
 * desc    : 三元组
 * </p>
 *
 * @author weilin
 * @since 30/8/2023
 */
public class Triple<T, U, V> {
    private T first;
    private U second;
    private V third;

    public Triple() {
    }

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public void setThird(V third) {
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    /**
     * 转为字符串 like (1,2,3) (2,3,4) (3,4,5)
     *
     * @return  (1,2,3)
     */
    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) object;
        return Objects.equals(first, triple.first) && Objects.equals(second, triple.second) && Objects.equals(third, triple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
