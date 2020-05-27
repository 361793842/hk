package com.hk.hkhttpclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})*/
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.hk.hkhttpclient.mapper")

public class HkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkApplication.class, args);
    }

}
