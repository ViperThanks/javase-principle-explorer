package com.yiren.principle.javase.object;


import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.test_data.TestData;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * desc     : 面试题 : 一个空对象占用多少字节
 */
@Slf4j
public class ObjectBytesDetailExplorer implements Explorer {


  /**
   * 调用 {@linkplain ClassLayout#parseInstance(Object)} 方法再打印出来就行了
   */
  public void explore() {
    System.out.println(ClassLayout.parseInstance(TestData.getEmptyUser()).toPrintable());
    System.out.println("\n=======happy everyday=======\n");
    System.out.println(ClassLayout.parseInstance(TestData.getTestUser()).toPrintable());
  }

  public static void main(String[] args) throws InterruptedException {
    Executor.executeExplorer(ObjectBytesDetailExplorer.class);
  }


}


