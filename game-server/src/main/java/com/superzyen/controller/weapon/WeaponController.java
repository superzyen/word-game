package com.superzyen.controller.weapon;

import com.superzyen.domain.request.InfoRequest;
import com.superzyen.service.weapon.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

    @Autowired
    WeaponService weaponService;

    @PostMapping("/setup/info")
    public String info(@RequestBody InfoRequest request) {
        return weaponService.setupInfo(request.getUserId());
    }

    @PostMapping("/warehouse")
    public String warehouse(@RequestBody InfoRequest request) {
        return weaponService.warehouse(request.getUserId());
    }
}
