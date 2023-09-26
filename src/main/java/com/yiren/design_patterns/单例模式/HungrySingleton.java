package com.yiren.design_patterns.单例模式;

import java.io.Serializable;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 15/9/2023
 */
public class HungrySingleton implements Serializable {

  private HungrySingleton(){}

  private static final HungrySingleton instance = new HungrySingleton();

  public static HungrySingleton getInstance() {
    return instance;
  }

  public Object readResolve(){
    return instance;
  }
}
