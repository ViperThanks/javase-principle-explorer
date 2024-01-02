package com.yiren.core;


/**
 * desc    : 执行者类，封装了一些执行方法
 * 用反射去实现的，就是不想每次都去new一个对象
 */
public class Executor {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("Explorer Core Executor");

  private static final String BEGIN_SIGN = "------------>";
  private static final String END_SIGN = "<------------";

  public static void executeExplorer(Explorer explorer) {
    try {
      LOGGER.info("{} execute explorerClass: {} {}\n", BEGIN_SIGN, explorer.getClass().getSimpleName(), END_SIGN);
      explorer.explore();
    } catch (Exception e) {
      LOGGER.error("catch explorer inner error \n ", e);
    }
    LOGGER.info("{} execute explorerClass: {} main thread end {}\n", BEGIN_SIGN, explorer.getClass().getSimpleName(), END_SIGN);
  }

  /**
   * 通过字节码对象创建对象,并执行
   *
   * @param explorerClazzs 探索者类的Class对象
   */
  @SafeVarargs
  public static void executeExplorer(Class<? extends Explorer>... explorerClazzs) {
    for (Class<? extends Explorer> explorerClazz : explorerClazzs) {
      if (explorerClazz == null) {
        LOGGER.error("explorer is null");
        continue;
      }
      String clazzName = explorerClazz.getSimpleName();
      LOGGER.info("{} execute explorerClass: [{}] main thread start {}", BEGIN_SIGN, clazzName, END_SIGN);
      Explorer explorer = null;
      try {
        explorer = explorerClazz.getConstructor().newInstance();
      } catch (Exception e) {
        LOGGER.error("{} execute explorerClass: [{}] error {}", BEGIN_SIGN, clazzName, END_SIGN);
        LOGGER.error("ensure that the explorerClass has a no-args constructor");
        LOGGER.error("ensure that the explorerClass constructor is public");
      }
      if (explorer == null) {
        LOGGER.error("explorer is null, skip this explorerClass: [{}] ", clazzName);
        continue;
      }
      boolean success = true;
      try {
        explorer.explore();
      } catch (Exception e) {
        LOGGER.error("catch inner exception", e);
        success = false;
      }
      LOGGER.info("{} execute explorerClass: [{}] main thread end because {} {}", BEGIN_SIGN, clazzName,
          success ? "execution completed" : "upper exception", END_SIGN);
    }
  }

}
