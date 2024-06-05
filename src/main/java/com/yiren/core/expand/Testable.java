package com.yiren.core.expand;

import com.yiren.entity.User;

import java.time.LocalDateTime;

import static com.yiren.utils.CommonUtils.getRandomLong;
import static com.yiren.utils.CommonUtils.getRandomString;

/**
 * desc: 有测试数据
 *
 * @author weilin
 * @since 2023-12-23
 */
public interface Testable {

  /**
   * 空用户
   */
  User emptyUser = new User();

  /**
   * 测试用户 有所有的属性
   */
  User testUser = new User()
      .setId(1L)
      .setPhone("12345678901")
      .setPassword("123456")
      .setNickName("test")
      .setIcon("test")
      .setCreateTime(LocalDateTime.now())
      .setUpdateTime(LocalDateTime.now());

  default User getTestUser() {
    return getUser();
  }

  static User getUser(){
    return new User()
        .setId(getRandomLong(1000))
        .setPhone(getRandomString(11))
        .setPassword(getRandomString(15, true))
        .setNickName(getRandomString(5))
        .setIcon(getRandomString(5))
        .setCreateTime(LocalDateTime.now())
        .setUpdateTime(LocalDateTime.now());
  }
}
