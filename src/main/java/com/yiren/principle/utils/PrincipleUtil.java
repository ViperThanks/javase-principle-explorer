package com.yiren.principle.utils;

import com.yiren.entity.PrincipleField;
import java.lang.reflect.Field;
import lombok.SneakyThrows;
import sun.misc.Unsafe;

/**
 * <p>
 * desc: 整个原理
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public final class PrincipleUtil {

  private PrincipleUtil() {
  }

  private static final Class<Unsafe> UNSAFE_CLASS = Unsafe.class;
  private static final String UNSAFE_FIELD_NAME = "theUnsafe";

  private static volatile Unsafe unsafe;

  /**
   * 获取unsafe 基于double check lock的懒汉单例 + 反射
   *
   * @return unsafe
   */
  @SneakyThrows
  public static Unsafe getUnsafe() {
    if (unsafe == null) {
      synchronized (PrincipleUtil.class) {
        if (unsafe == null) {
          Field unsafeField = UNSAFE_CLASS.getDeclaredField(UNSAFE_FIELD_NAME);
          unsafeField.setAccessible(true);
          unsafe = (Unsafe) unsafeField.get(null);
        }
      }
    }
    return unsafe;
  }

  /**
   * 反射获取这个对象的这个字段对象,
   * @param targetObj 目标对象
   * @param fieldName 字段名
   * @return 对象
   */
  public static PrincipleField getFiled(Object targetObj, String fieldName) {
    return new PrincipleField(targetObj, fieldName);
  }

  @SneakyThrows
  public static <T> T getStaticFiled(Class<?> clazz, String fieldName) {
    Field declaredField = clazz.getDeclaredField(fieldName);
    declaredField.setAccessible(true);
    return (T) declaredField.get(null);
  }
}
