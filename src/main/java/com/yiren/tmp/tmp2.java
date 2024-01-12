package com.yiren.tmp;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.core.expand.BaseTest;
import java.time.ZoneId;
import java.util.function.IntBinaryOperator;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-23
 */
public class tmp2 extends BaseTest implements Explorer {

  public static void main(String[] args) {
    Executor.executeExplorer(tmp2.class);
  }


  /**
   * 探索 方法
   *
   * @throws Exception
   */
  @Override
  public void explore() throws Exception {
    ZoneId zoneId = ZoneId.systemDefault();
    LOGGER.info(zoneId.toString());

  }

  public int calculateNum(IntBinaryOperator operator) {
    int a = 10, b = 20;
    return operator.applyAsInt(a, b);
  }
}

class User1{
  private String id;

  private String name;

  private Integer age;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}