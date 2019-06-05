package com.mortal.hua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.mortal.hua.dao")
@RestController
public class HuaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuaApplication.class, args);
    }

}
