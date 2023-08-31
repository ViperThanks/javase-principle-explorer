package com.yiren.principle.javase.jdbc;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */

public class JdbcProperties {
  static final String driverClassName = "com.mysql.jdbc.Driver";
  static final String url = "jdbc:mysql://localhost:3306/daily_use_only?useUnicode=true&characterEncoding=utf8&useSSL=false";
  static final String username = "root";
  static final String password = "root";
  static final int port = 3306;

  static String getDriverClassName() {
    return driverClassName;
  }

  static String getUrl() {
    return url;
  }

  static String getUsername() {
    return username;
  }

  static String getPassword() {
    return password;
  }

  static int getPort() {
    return port;
  }

}
