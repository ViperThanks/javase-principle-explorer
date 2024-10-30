package com.yiren.core;

import com.yiren.core.enums.ExplorerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 保留到运行时
@Target(ElementType.TYPE) // 作用于类
public @interface Explore {
    /**
     * 探索类型
     */
    ExplorerType value() default ExplorerType.NONE;

    String desc() default "";
}