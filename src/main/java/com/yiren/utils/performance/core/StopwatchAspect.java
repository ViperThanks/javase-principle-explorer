package com.yiren.utils.performance.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 2/9/2023
 */
@Aspect
@Deprecated
public class StopwatchAspect {

  private static final Logger log = LoggerFactory.getLogger(StopwatchAspect.class);


  @Around("@annotation(com.yiren.utils.performance.core.Stopwatch)")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.nanoTime();

    Object result = joinPoint.proceed(); // 调用目标方法

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1_000_000; // 转换为毫秒

    System.out.println("Method '" + joinPoint.getSignature().getName() + "' took " + duration + " ms to execute.");

    return result;
  }


}
