package com.example.priority.testpriority;

import com.example.priority.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportResource({"classpath*:spring-mybaties.xml"})
@ComponentScan("com.*")
public class TestpriorityApplication {
    public static void main(String[] args) {
     ConfigurableApplicationContext context = SpringApplication.run(TestpriorityApplication.class, args);
        Test test =context.getBean(Test.class);
        test.SystemString();
    }

}
