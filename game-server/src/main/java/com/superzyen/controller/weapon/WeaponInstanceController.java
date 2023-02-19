package com.superzyen.controller.weapon;

import com.superzyen.domain.request.InstanceExistRequest;
import com.superzyen.domain.request.SellInstanceRequest;
import com.superzyen.service.weapon.WeaponInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weaponinstance")
public class WeaponInstanceController {

    @Autowired
    private WeaponInstanceService weaponInstanceService;

    @PostMapping("/setup")
    public String setup(@RequestBody InstanceExistRequest request) {
        return weaponInstanceService.setup(request.getInstanceId(), request.getUserId());
    }

    @PostMapping("/sell")
    public String sell(@RequestBody SellInstanceRequest request) {
        return weaponInstanceService.sell(request.getInstanceId(), request.getUserId(), request.getPrice());
    }
}
