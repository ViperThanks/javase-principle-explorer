package com.yiren.test_data;

import com.yiren.entity.Employee;
import com.yiren.entity.User;

import java.time.LocalDateTime;

/**
 * desc  :
 * author: wl
 * date  : 17/6/2023 下午 1:53
 * email : vieper0714@outlook.com
 */
public class TestData {

  /**
   * 空用户
   */
  private static final User emptyUser = new User();

  /**
   * 测试用户
   * 有所有的属性
   */
  private static final User testUser = new User()
    .setId(1L)
    .setPhone("12345678901")
    .setPassword("123456")
    .setNickName("test")
    .setIcon("test")
    .setCreateTime(LocalDateTime.now())
    .setUpdateTime(LocalDateTime.now());

  /**
   * 测试员工
   */
  private static final Employee testEmployee = new Employee()
    .setUsername("test");

  /**
   * 空员工
   */
  private static final Employee emptyEmployee = new Employee();


  public static Employee getEmptyEmployee() {
    return emptyEmployee;
  }

  public static Employee getTestEmployee() {
    return testEmployee;
  }

  public static User getEmptyUser() {
    return emptyUser;
  }

  public static User getTestUser() {
    return testUser;
  }
}
