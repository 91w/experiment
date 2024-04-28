package org.wgh.handshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步任务
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
@MapperScan("org.wgh.handshop.mapper")
public class SecondShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondShopApplication.class, args);
    }

}
