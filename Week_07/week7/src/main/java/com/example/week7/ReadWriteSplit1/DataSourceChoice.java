package com.example.week7.ReadWriteSplit1;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DataSourceChoice  {



  public void  readBySlave(DataSource dataSource2) throws SQLException {
      Connection connection =dataSource2.getConnection();
      PreparedStatement statement = connection.prepareStatement("select * from order_info");
      ResultSet rs = statement.executeQuery();
      while(rs.next()){
          System.out.println(rs.getString("id"));
      }
      connection.close();
  }

  public void insertByMaster(DataSource dataSource1) throws SQLException {

      Connection connection =dataSource1.getConnection();
      PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `order_info`(`id`, `user_id`, `item_id`, `item_price`, `amount`, `order_price`, `promo_id`) VALUES ('2', 3, 2, 1, 1, 1, 0)");
      boolean istrue =statement1.execute();
      System.out.println(istrue+"***********");
      connection.close();

  }
}
