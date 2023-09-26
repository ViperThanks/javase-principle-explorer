package com.yiren.utils;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public final class Required {

    /**
     * 抄 caffeine 的
     * @param expectStatus 期待的状态
     * @param errMessage 错误信息
     */
    public static void requiredStatus(boolean expectStatus, String errMessage) {
        if (!expectStatus)
            throw new IllegalArgumentException(errMessage);
    }

    /**
     * 上面的重载方法
     * @param expectStatus 期待的状态
     */
    public static void requiredStatus(boolean expectStatus) {
        if (!expectStatus)
            throw new IllegalArgumentException();
    }


    static void requiredNonnegative(int value, String name) {
        if (value < 0)
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }


    static void requiredNonnegative(long value, String name) {
        if (value < 0)
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }



}
