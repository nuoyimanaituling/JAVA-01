package com.example.week7.ReadWriteSplit2;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@SpringBootApplication
public class MainService2 {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MainService2.class, args);
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement1 = connection.prepareStatement("INSERT INTO `order_info`(`id`, `user_id`, `item_id`, `item_price`, `amount`, `order_price`, `promo_id`) VALUES ('3', 3, 2, 1, 1, 1, 0)");
             PreparedStatement statement2 = connection.prepareStatement("select * from order_info");
        ) {
            statement1.execute();
            rs = statement2.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `order_info`");
        ) {
            rs = statement.executeQuery();
            // 重新读走的从库
            while (rs.next()) {
                System.out.println(rs.getBigDecimal("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    public DataSource dataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jdbc:mysql://47.94.149.219:3306/testmysql?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("masterDataBase", dataSource1);
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/miaosha?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("slaveDataBase", dataSource2);
        //配置读写分离规则
        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "masterDataBase", Arrays.asList("slaveDataBase"), "load_balancer"));
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);
        //创建DS
        try {
            return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Arrays.asList(ruleConfiguration), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
