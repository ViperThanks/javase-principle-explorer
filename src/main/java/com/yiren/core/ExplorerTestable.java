package com.yiren.core;

import static com.yiren.utils.CommonUtils.getRandomLong;
import static com.yiren.utils.CommonUtils.getRandomString;

import com.yiren.entity.User;
import java.time.LocalDateTime;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author weilin
 * @since 19/9/2023
 */
public interface ExplorerTestable extends Explorer {

  /**
   * 空用户
   */
  User emptyUser = new User();

  /**
   * 测试用户
   * 有所有的属性
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
