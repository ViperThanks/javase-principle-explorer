package com.yiren.design_patterns.单例模式;

/**
 * <p>
 * desc:  基于dcl double checked locking 的饿汉模式的单例模式
 * </p>
 *
 * @author weilin
 * @since 15/9/2023
 */
public class LazySingleton {

  //ensure
  private LazySingleton(){}

  private
  volatile
  static
  LazySingleton INSTANCE = null;

  public static LazySingleton getInstance(){
    // ---- 读屏障
    if (INSTANCE == null) {
      synchronized (LazySingleton.class) {
        if (INSTANCE == null) {
          INSTANCE =
            new LazySingleton();
          // ---写屏障
        }
      }
    }
    return INSTANCE;
  }





}
