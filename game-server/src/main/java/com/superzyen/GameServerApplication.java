package com.superzyen;

import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.superzyen.mapper")
public class GameServerApplication {
    @RequestMapping("/")
    String home() {
        return "Hello World! 雪宝爱疯13还在分期中!";
    }

    public static void main(String[] args) {
        SpringApplication.run(GameServerApplication.class, args);
    }

    @Bean
    public PostgreKeyGenerator oracleKeyGenerator() {
        return new PostgreKeyGenerator();
    }
}
