package com.yiren.principle.jvm.heap;

import com.google.common.primitives.Bytes;
import com.yiren.core.Explorer;
import com.yiren.utils.Memory;
import com.yiren.utils.MemoryUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * desc  :
 * author: weilin
 * date  : 14/8/2023
 */

public class GCAnalyzerExplorer implements Explorer {


  @Override
  public void explore() {

  }


  public static void main(String[] args) {
    //gc option
    // -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    List<byte[]> list = new ArrayList<>();
    ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
    byte[] bytes = MemoryUtil.getBytes(100, Memory.GB);
    System.out.println(bytes.length);
    threadLocal.set(MemoryUtil.getBytes(5));
    list.add(MemoryUtil.getBytes(5));
    list.add(MemoryUtil.getBytes(100, Memory.GB));
    list.add(MemoryUtil.getBytes(4));
    System.out.println(threadLocal.get());

  }
}
