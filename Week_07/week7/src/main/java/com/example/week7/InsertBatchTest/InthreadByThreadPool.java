package com.example.week7.InsertBatchTest;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class InthreadByThreadPool {

//采用线程池+批处理总共耗时122338
    //采用单线程大概需要一个小时到半小时
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(InthreadByThreadPool.class, args);

        CountDownLatch startCountDownLatch = new CountDownLatch(13);
        CountDownLatch endCountDownLatch = new CountDownLatch(13);
        for (int i = 0; i < 13; i++) {
            Thread thread = new Thread(()->{
                try {
                    insertByMaster(100_0000/13,dataSource2(), startCountDownLatch);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                endCountDownLatch.countDown();
            });
            thread.start();
        }
        try {
            startCountDownLatch.await();
            long startMillis = System.currentTimeMillis();
            endCountDownLatch.await();
            long endMillis = System.currentTimeMillis();
            System.out.printf("总共耗时%d\n", endMillis - startMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void insertByMaster(int count,DataSource dataSource1, CountDownLatch startCountDownLatch) throws SQLException, InterruptedException {

        Connection connection =dataSource1.getConnection();
        PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `order_info`(`id`, `user_id`, `item_id`, `item_price`, `amount`, `order_price`, `promo_id`) VALUES ('4', 3, 2, 1, 1, 1, 0)");
        startCountDownLatch.countDown();
        startCountDownLatch.await();
        for (int i=0;i<count;i++){
            statement1.addBatch();
        }
        statement1.executeBatch();

    }



    public static DataSource dataSource2() {
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setJdbcUrl("jdbc:mysql://47.94.149.219:3306/testmysql?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        return dataSource2;
    }




}
