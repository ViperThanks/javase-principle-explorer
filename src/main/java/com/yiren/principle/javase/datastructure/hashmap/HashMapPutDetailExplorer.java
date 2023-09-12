package com.yiren.principle.javase.datastructure.hashmap;

import com.yiren.core.Explorer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * desc  : 面试题 hashmap的put的底层实现
 * author: weilin
 * date  : 8/7/2023
 */
@Slf4j
public class HashMapPutDetailExplorer implements Explorer {


  @Override
  public void explore() {

  }

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    HashMap<String, String> map = new HashMap<>();
    //反射获得属性 threshold
    Field threshold = map.getClass().getDeclaredField("threshold");
    threshold.setAccessible(true);
    //获得数值
    int value1 = threshold.getInt(map);
    log.info("map懒加载 ");
    log.warn("当前 threshold的值为 :{}", value1);
    //put 了一个对象 threshold变成 16 * 0.75(DEFAULT_LOAD_FACTOR) = 12
    map.put("", "");
    int value2 = threshold.getInt(map);

    log.info("put 一个元素进去之后 ");
    log.warn("当前 threshold的值为 :{}", value2);

    for (int i = 0; i < 11; i++) {
      String s = String.valueOf(i);
      map.put(s, s);
    }
    int value3 = threshold.getInt(map);
    log.info("现在map有12个元素,下一次将会扩容  ");
    log.warn("当前 threshold的值为 :{}", value3);
    map.put(String.valueOf(Integer.MAX_VALUE), "1");
    int value4 = threshold.getInt(map);
    //两倍扩容,为什么是两倍,看面经 1.7 1.8的区别
    log.info("扩容之后 ");
    log.warn("当前 threshold的值为 :{}", value4);

  }
}