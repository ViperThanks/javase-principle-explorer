package com.yiren.principle.juc.Unsafe;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.principle.utils.PrincipleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 16/9/2023
 */
public class UnsafeFunctionExplorer implements Explorer {

  private static final Logger LOGGER = LoggerFactory.getLogger(UnsafeFunctionExplorer.class);

  /**
   * 探索 方法
   *
   * @throws Exception 可能出现的异常
   */
  @Override
  public void explore() throws Exception {
    Unsafe unsafe = PrincipleUtil.getUnsafe();
    long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
    long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));
    Teacher teacher = new Teacher();
    teacher.id = 1;
    teacher.name = "alkjdf";
    unsafe.compareAndSwapInt(teacher, idOffset, 1, 2);
    unsafe.compareAndSwapObject(teacher, nameOffset, "alkjdf", "java yyds");
    LOGGER.info(String.valueOf(teacher));


  }

  public static void main(String[] args) {
    Executor.executeExplorer(UnsafeFunctionExplorer.class);
  }


}

class Teacher{
  volatile int id;
  volatile String name;

  @Override
  public String toString() {
    return "Teacher{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}