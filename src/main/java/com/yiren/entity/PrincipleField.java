package com.yiren.entity;

import java.lang.reflect.Field;

/**
 * desc: 原理实体字段
 *
 * @author weilin
 * @since 2023-11-3
 */
public class PrincipleField {
  private transient Object data;
  private final Object targetObj;

  private final String fieldName;

  public PrincipleField(Object targetObj, String fieldName) {
    this.targetObj = targetObj;
    this.fieldName = fieldName;
  }


  @SuppressWarnings("unchecked")
  public <T> T getByType() {
    return (T) get();
  }


  public Object get(){
    flush();
    return data;
  }

  private void flush() {
    try {
      Field declaredField = targetObj.getClass().getDeclaredField(fieldName);
      declaredField.setAccessible(true);
      data = declaredField.get(targetObj);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
