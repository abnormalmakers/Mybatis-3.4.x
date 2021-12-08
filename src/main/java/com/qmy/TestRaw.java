package com.qmy;

import com.qmy.entites.User;

import java.sql.*;

public class TestRaw {

  public static void main(String[] args) throws Exception {

    Connection connection = DriverManager.getConnection("jdbc:mysql:///jt_db","root","123456");
    String sql = "select * from user";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){
      int id = resultSet.getInt("id");
      String username = resultSet.getString("username");
      String password = resultSet.getString("password");
      User user = new User(id, username, password);
      System.out.println(user);
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();

  }
}
