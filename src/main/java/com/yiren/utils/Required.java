package com.yiren.utils;

/**
 * <p>
 * desc: 抄 caffeine 要求符合状态
 * </p>
 *
 * @author weilin
 * @since 6/9/2023
 */
public final class Required {

    /**
     * 如果不符合状态就 throw 异常
     *
     * @param expectStatus 期待的状态
     * @param errMessage   错误信息
     */
    public static void requiredStatus(boolean expectStatus, String errMessage) {
        if (!expectStatus)
            throw new IllegalArgumentException(errMessage);
    }

    /**
     * 如果不符合状态就 throw 异常
     *
     * @param expectStatus 期待的状态
     */
    public static void requiredStatus(boolean expectStatus) {
        if (!expectStatus)
            throw new IllegalArgumentException();
    }


    /**
     * 要求非负数
     *
     * @param value int型的值
     * @param name  参数的名字
     */
    public static void requiredNonnegative(int value, String name) {
        if (value < 0)
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }


    /**
     * 要求非负数
     *
     * @param value long型的值
     * @param name  参数名字
     */
    public static void requiredNonnegative(long value, String name) {
        if (value < 0)
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }

    /**
     *  要求正数
     * @param value int 型的值
     * @param name 参数名字
     */
    public static void requirePositive(int value, String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be positive but was: " + value);
        }
    }


}
