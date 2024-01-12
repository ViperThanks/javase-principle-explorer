package com.yiren.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
