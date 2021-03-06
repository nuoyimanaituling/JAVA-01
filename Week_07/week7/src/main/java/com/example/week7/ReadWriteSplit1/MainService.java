package com.example.week7.ReadWriteSplit1;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class MainService {



    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MainService.class, args);

        DataSourceChoice choice = applicationContext.getAutowireCapableBeanFactory().getBean(DataSourceChoice.class);
          choice.insertByMaster(dataSource2());
          choice.readBySlave(dataSource1());



    }


    @Bean
    public static DataSource dataSource1() {
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/miaosha?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        return dataSource1;
    }

    @Bean
    public static DataSource dataSource2() {
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setJdbcUrl("jdbc:mysql://47.94.149.219:3306/testmysql?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        return dataSource2;
    }

}
