package com.superzyen.controller.soldier;

import com.superzyen.domain.request.InfoRequest;
import com.superzyen.service.soldier.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soldier")
public class SoldierController {
    @Autowired
    SoldierService soldierService;

    @PostMapping("/info")
    public String show(@RequestBody InfoRequest request) {
        return soldierService.info(request.getUserId());
    }
}
