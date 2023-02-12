package com.superzyen;

import com.superzyen.page.WelcomPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class GameClientApplication {

    @GetMapping("/")
    String home() {
        return "Hello World! 我是客户端";
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GameClientApplication.class, args);
        WelcomPage welcomPage=new WelcomPage();
        welcomPage.start();
    }
}
