package com.example.priority.config;

import com.example.priority.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean("testbean")
    public TestBean initTestBean(){
        return  new TestBean();
    }
}


