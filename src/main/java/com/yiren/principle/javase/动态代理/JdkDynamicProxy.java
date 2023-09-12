package com.yiren.principle.javase.动态代理;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 7/9/2023
 */

public class JdkDynamicProxy implements InvocationHandler {

  private static final Logger log = LoggerFactory.getLogger(JdkDynamicProxy.class);


  private Object targetObj;
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    log.info("记日志");
    Object invoke = method.invoke(targetObj, args);
    log.info("完成日志");
    return invoke;
  }

  public Object getInstance(Object targetObj) {
    this.targetObj = targetObj;
    return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
  }


}
