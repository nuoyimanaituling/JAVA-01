package com.example.week5;


import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class BeaaInJava {

    @Bean(name = "Klass")
    public Klass getKlass(){

        return new Klass();
    }


    @Bean(name = "student")
    public  Student getStudent(){
        log.info("生成student");
        return  new Student();
    }

    @Bean
    public  School getSchool(){
        log.info("生成school");
        return new School(this.getKlass(),this.getStudent());
    }


}
