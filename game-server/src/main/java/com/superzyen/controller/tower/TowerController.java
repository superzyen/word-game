package com.superzyen.controller.tower;

import com.superzyen.domain.request.AttackRequest;
import com.superzyen.domain.request.InfoRequest;
import com.superzyen.service.tower.TowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/tower")
public class TowerController {

    @Autowired
    private TowerService towerService;

    @RequestMapping("/show")
    public String show(@RequestBody InfoRequest request) {
        return towerService.show(request.getUserId());
    }

    @RequestMapping("/attack")
    public String attack(@RequestBody AttackRequest request) {
        request.setTime(new Date());
        return towerService.attack(request);
    }
}
