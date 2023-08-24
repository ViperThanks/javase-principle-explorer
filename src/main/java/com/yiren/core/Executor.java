package com.yiren.core;

import com.yiren.algorithm.AlgorithmTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 * desc    : 执行者类，封装了一些执行方法
 * 用反射去实现的，就是不想每次都去new一个对象
 */
public class Executor {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Executor.class);


  public static void executeExplorer(Explorer explorer) {
    try {
      explorer.explore();
    } catch (Exception e) {
      log.error("不能正常启动" + explorer.getClass());
    }
  }

  /**
   * 通过字节码对象创建对象,并执行
   *
   * @param explorerClazzs 探索者类的Class对象
   */
  @SafeVarargs
  public static void executeExplorer(Class<? extends Explorer>... explorerClazzs) {

    for (Class<? extends Explorer> explorerClazz : explorerClazzs) {
      try {
        log.info("execute explorerClass: {} \n", explorerClazz.getSimpleName());
        Explorer explorer = explorerClazz.getConstructor().newInstance();
        explorer.explore();
      } catch (Exception e) {
        log.error("execute explorerClass:{} error", explorerClazz.getSimpleName());
        log.error("ensure that the explorerClass has a no-args constructor");
        log.error("ensure that the explorerClass constructor is public");
        log.error("ensure catch all exception");
      }
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
        e.printStackTrace();
      }
    }
  }

}
