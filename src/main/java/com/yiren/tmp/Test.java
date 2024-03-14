package com.yiren.tmp;

import com.google.common.collect.ImmutableMap;
import com.yiren.algorithm.datastructure.Pair;
import com.yiren.utils.MemoryUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 4/9/2023
 */
public class Test {

  private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


  public static void main(String[] args) throws Exception {
    //map的迭代方式
    Map<String, String> alphaNumMap = ImmutableMap.of("a", "1", "b", "2", "c", "3");
    //基于key set的
    //1. 增强for循环
    for (String alpha : alphaNumMap.keySet()) {}
    //2.forEach api
    alphaNumMap.keySet().forEach((key) -> System.out.println(key + alphaNumMap.get(key)));
    //3.stream流
    alphaNumMap.keySet().stream().forEach((key) -> System.out.println(key + alphaNumMap.get(key)));
    //基于entrySet的
    //1.增强for循环
    for (Entry<String, String> stringStringEntry : alphaNumMap.entrySet()) {}
    //2.forEach api
    alphaNumMap.entrySet().forEach((entry) -> System.out.println(entry.getKey() + entry.getValue()));
    //3.stream流
    alphaNumMap.entrySet().stream().forEach((entry) -> System.out.println(entry.getKey() + entry.getValue()));
    //对于整个map的操作
    //1.forEach api
    alphaNumMap.forEach((key,value) -> System.out.println(key + value));
    //迭代器
    Iterator<String> iterator = alphaNumMap.keySet().iterator();
    //
    Iterator<Entry<String, String>> iterator1 = alphaNumMap.entrySet().iterator();
    //只用value
    alphaNumMap.values().forEach(value -> System.out.println(value));
    for (String value : alphaNumMap.values()) {}

  }


  private static void generateFile() throws Exception {

    String s1 = Optional.ofNullable("hello")
        .map(
            s -> {
              return s.equals("hell") + "1";
            }
        ).orElse("hello");

    String fatherPath = "D:\\Tmp\\test_data\\";
    String _1GB = "_1GB.txt";
    File _1GBFile = new File(fatherPath + _1GB);
    OutputStream stream = Files.newOutputStream(_1GBFile.toPath(), StandardOpenOption.WRITE);
    byte[] buffed = MemoryUtil.getBytes(1 << 2);
    Arrays.fill(buffed, (byte) 6);
    for (int i = 0; i < 1 << 8; i++) {
      stream.write(buffed, 0, buffed.length - 1);
    }
    stream.flush();
    stream.close();


  }


  @org.junit.jupiter.api.Test
  public void test1() {
    Pair<Integer, Integer> pair = new Pair<>(1, 2);
    Integer key = pair.getFirst();
    System.out.println(key);
  }
}
