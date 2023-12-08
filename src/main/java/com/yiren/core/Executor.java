package com.yiren.core;

import com.yiren.algorithm.AlgorithmTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 * desc    : 执行者类，封装了一些执行方法
 * 用反射去实现的，就是不想每次都去new一个对象
 */
public class Executor {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Executor.class);


  public static void executeExplorer(Explorer explorer) {
    try {
      LOGGER.info("--- --- --- ---execute explorerClass: {} \n", explorer.getClass().getSimpleName());
      explorer.explore();
    } catch (Exception e) {
      LOGGER.error("catch explorer inner error \n ", e);
    }
    LOGGER.info("--- --- --- ---execute explorerClass: {} main thread end \n", explorer.getClass().getSimpleName());
  }

  /**
   * 通过字节码对象创建对象,并执行
   *
   * @param explorerClazzs 探索者类的Class对象
   */
  @SafeVarargs
  public static void executeExplorer(Class<? extends Explorer>... explorerClazzs) {
    for (Class<? extends Explorer> explorerClazz : explorerClazzs) {
      String clazzName = explorerClazz.getSimpleName();
      LOGGER.info("--- --- --- --- execute explorerClass: [{}] main thread start", clazzName);
      Explorer explorer = null;
      try {
        explorer = explorerClazz.getConstructor().newInstance();
      } catch (Exception e) {
        LOGGER.error("execute explorerClass: [{}] error", clazzName);
        LOGGER.error("ensure that the explorerClass has a no-args constructor");
        LOGGER.error("ensure that the explorerClass constructor is public");
      }
      if (explorer == null) {
        LOGGER.error("explorer is null, skip this explorerClass: [{}] ", clazzName);
        continue;
      }
      long cost = 0L;
      try {
        long start = System.currentTimeMillis();
        explorer.explore();
        long end = System.currentTimeMillis();
        cost = end - start;
      } catch (Exception e) {
        LOGGER.error("catch inner error \n ", e);
      }
      LOGGER.info("--- --- --- --- execute explorerClass: [{}] main thread end cost {} ms\n", clazzName, cost);
    }
  }

  /**
   * 通过字节码对象创建对象,并执行
   *
   * @param algoClazzs 算法模板类的Class对象
   */
  @SafeVarargs
  public static void executeAlgo(Class<? extends AlgorithmTemplate>... algoClazzs) {

    for (Class<? extends AlgorithmTemplate> algoClazz : algoClazzs) {
      try {
        LOGGER.info("execute explorerClass: {} \n", algoClazz.getSimpleName());
        AlgorithmTemplate template = algoClazz.getConstructor().newInstance();
        template.execute();
      } catch (InstantiationException
               | IllegalAccessException
               | NoSuchMethodException
               | InvocationTargetException e) {
        LOGGER.error("execute explorerClass:{} error", algoClazz.getSimpleName());
        LOGGER.error("ensure that the explorerClass has a no-args constructor");
        LOGGER.error("ensure that the explorerClass constructor is public");
      }
    }
  }

}
