package com.yiren.utils;

import java.util.function.BinaryOperator;

/**
 * desc: 匿名表达式工具类
 *
 * @author Viper Thanks
 * @since 30/5/2024
 */
public final class LambadaUtils {
    /**
     * 要最后的值
     */
    public static <T> BinaryOperator<T> keepingLast(){
        return (a, b) -> b;
    }

    /**
     * 要最开始的值
     */
    public static <T> BinaryOperator<T> keepingFirst(){
        return (a, b) -> a;
    }


}
