package com.yiren.principle.javase.jdbc;

import com.yiren.core.Executor;
import com.yiren.core.Explorer;
import com.yiren.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;

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
      PreparedStatement prepared = connection.prepareStatement(" select * from user where id = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      prepared.setInt(1, 1);
      ResultSet resultSet1 = prepared.executeQuery();
      while (resultSet1.next()) {
        int anInt = resultSet1.getInt("id");
        String string = resultSet1.getString("name");
        log.info("id: {}, name: {}", anInt, string);
      }
    } catch (ClassNotFoundException
             | SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public static void main(String[] args) {
    Executor.executeExplorer(JdbcExplorer.class);
  }

}
