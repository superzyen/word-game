package com.superzyen.controller.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("/zyen")
    public String zyen(){
        return "welcome zyen game";
    }
}
