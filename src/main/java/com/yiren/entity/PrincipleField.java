package com.yiren.entity;


import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Field;

/**
 * desc: 原理实体字段
 * ex: 如为了找到arrayList的底层数组 ->
 * new PrincipleField(new ArrayList(),"elementData",Object[].class)
 * @author weilin
 * @since 2023-11-3
 */
public class PrincipleField<T> {
    /**
     * 具体数据
     */
    private T data;
    /**
     * 反射对象
     */
    private final Object targetObj;
    /**
     * 反射的字段
     */
    private final Field targetField;
    /**
     * 反射的类
     */
    private final Class<?> cl;

    public PrincipleField(Class<?> targetClass, String fieldName, Class<T> tClass) {
        this(targetClass, null, fieldName);
    }

    public PrincipleField(Class<?> targetClass, String fieldName, TypeReference<T> typeReference) {
        this(targetClass, null, fieldName);
    }


    /**
     * 构建实体
     * @param targetObj 反射的目标对象
     * @param fieldName 反射的字段名称
     * @param tClass 反射的字段的类型
     */
    public PrincipleField(Object targetObj, String fieldName, Class<T> tClass) {
        this(targetObj.getClass(), targetObj, fieldName);
    }

    /**
     * 构建实体
     * @param targetObj 反射的目标对象
     * @param fieldName 反射的字段名称
     * @param typeReference 基于TypeReference实现泛型的泛型
     */
    public PrincipleField(Object targetObj, String fieldName, TypeReference<T> typeReference) {
        this(targetObj.getClass(), targetObj, fieldName);
    }

    public PrincipleField(Class<?> targetClass, Object targetObj, String fieldName) {
        this.targetObj = targetObj;
        this.cl = targetClass;
        try {
            this.targetField = targetClass.getDeclaredField(fieldName);
            this.targetField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public PrincipleField<T> withInit() {
        flush();
        return this;
    }

    /**
     * 获取最新的反射对象
     */
    public T get() {
        flush();
        return data;
    }

    /**
     * 获取上一次反射对象的快照，注意：引用类型不是快照
     */
    public T getSnapshot() {
        return data == null ? get() : data;
    }

    /**
     * 刷新data对象
     */
    @SuppressWarnings("unchecked")
    private void flush() {
        try {
            data = (T) targetField.get(targetObj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
