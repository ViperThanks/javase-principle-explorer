package com.yiren.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class User1 implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  /**
   * 手机号码
   */
  private String phone;
  /**
   * 密码，加密存储
   */
  private String password;
  /**
   * 昵称，默认是随机字符
   */
  private String nickName;
  /**
   * 用户头像
   */
  private String icon = "";
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 更新时间
   */
  private LocalDateTime updateTime;

}