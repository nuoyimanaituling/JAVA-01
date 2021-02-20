package com.example.priority;


import org.springframework.stereotype.Component;

@Component("testbean")
public class testBeanAnnotation implements  Test{
    @Override
    public void SystemString() {
        System.out.println("我是注解生成的");
    }
}
