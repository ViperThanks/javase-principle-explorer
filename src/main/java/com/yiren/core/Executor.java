package com.yiren.core;

import com.yiren.algorithm.AlgorithmTemplate;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * desc    : 执行者类，封装了一些执行方法
 * 用反射去实现的，就是不想每次都去new一个对象
 */
public class Executor {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Executor.class);


  public static void executeExplorer(Explorer explorer) {
    try {
      log.info("--- --- --- ---execute explorerClass: {} \n", explorer.getClass().getSimpleName());
      explorer.explore();
    } catch (Exception e) {
      log.error("catch explorer inner error \n ", e);
    }
    log.info("--- --- --- ---execute explorerClass: {} main thread end \n", explorer.getClass().getSimpleName());
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
      log.info("--- --- --- --- execute explorerClass: {} main thread start", clazzName);
      Explorer explorer = null;
      try {
        explorer = explorerClazz.getConstructor().newInstance();
      } catch (Exception e) {
        log.error("execute explorerClass:{} error", clazzName);
        log.error("ensure that the explorerClass has a no-args constructor");
        log.error("ensure that the explorerClass constructor is public");
      }
      if (explorer == null) {
        log.error("explorer is null, skip this explorerClass: {}", clazzName);
        continue;
      }
      try {
        explorer.explore();
      } catch (Exception e) {
        log.error("catch inner error \n ", e);
      }
      log.info("--- --- --- --- execute explorerClass: {} main thread end \n", clazzName);
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
        log.info("execute explorerClass: {} \n", algoClazz.getSimpleName());
        AlgorithmTemplate template = algoClazz.getConstructor().newInstance();
        template.execute();
      } catch (InstantiationException
               | IllegalAccessException
               | NoSuchMethodException
               | InvocationTargetException e) {
        log.error("execute explorerClass:{} error", algoClazz.getSimpleName());
        log.error("ensure that the explorerClass has a no-args constructor");
        log.error("ensure that the explorerClass constructor is public");
      }
    }
  }

}
