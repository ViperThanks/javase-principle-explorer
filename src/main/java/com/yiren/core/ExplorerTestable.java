package com.yiren.core;

import com.yiren.entity.Employee;
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


}
