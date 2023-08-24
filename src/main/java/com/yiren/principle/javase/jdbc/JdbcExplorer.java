package com.yiren.principle.javase.jdbc;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static com.yiren.principle.javase.jdbc.JdbcProperties.*;

/**
 * desc  :
 * author: weilin
 * date  : 17/7/2023
 */

public class JdbcExplorer implements Explorer {

  private static final Logger log = LoggerFactory.getLogger(JdbcExplorer.class);


  @Override
  public void explore(){
    try {
      Class.forName(getDriverClassName());
      Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
      Statement statement = connection.createStatement();
      String sql = "select * from user";
      ResultSet resultSet = statement.executeQuery(sql);
    } catch (ClassNotFoundException
             | SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public static void main(String[] args) {
    Executor.executeExplorer(JdbcExplorer.class);
  }

}
