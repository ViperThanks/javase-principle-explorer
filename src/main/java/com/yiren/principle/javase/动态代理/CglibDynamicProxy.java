package com.yiren.principle.javase.动态代理;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 7/9/2023
 */
public class CglibDynamicProxy implements MethodInterceptor {
  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    return null;
  }
}
