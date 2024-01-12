package com.yiren.tmp;

import cn.hutool.core.bean.BeanUtil;
import com.yiren.principle.utils.PrincipleUtil;

/**
 * desc:
 *
 * @author weilin
 * @since 2023-12-29
 */
public class TestHutoolBeanUtil {

  public static void main(String[] args) {
    Son sourceSon = new Son();
    sourceSon.setId(1L);
    sourceSon.setName(1);
    sourceSon.setExtraFiled(200);
    Father targetFather = new Father();
    BeanUtil.copyProperties(sourceSon, targetFather);
    System.out.println(targetFather);


    Father sourceFather = new Father();
    sourceFather.setId(1L);
    sourceFather.setName(2);

    Son targetSon = new Son();
    BeanUtil.copyProperties(sourceFather,targetSon);
    System.out.println(targetSon);
  }

  private static class Father{
    String name;
    Long id;

    public void setName(Integer name) {
      this.name = String.valueOf(name);
    }

    public String getName() {
      return name;
    }


    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    @Override
    public String toString() {
      return "Father{" +
          "name='" + name + '\'' +
          ", id=" + id +
          '}';
    }
  }

  private static class Son extends Father{
    private String extraFiled;
    public String getExtraFiled() {
      return extraFiled;
    }

    public void setExtraFiled(Integer extraFiled) {
      this.extraFiled = String.valueOf(extraFiled);
    }

    @Override
    public String toString() {
      return "Son{" +
          "extraFiled='" + extraFiled + '\'' +
          ", name='" + name + '\'' +
          ", id=" + id +
          '}';
    }
  }

}
