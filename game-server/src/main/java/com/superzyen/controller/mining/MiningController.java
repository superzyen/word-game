package com.superzyen.controller.mining;

import com.superzyen.domain.request.AwardRequest;
import com.superzyen.service.mining.MiningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiningController {
    @Autowired
    MiningService miningService;

    @PostMapping("/award")
    public Boolean award(@RequestBody AwardRequest request) {
        return miningService.award(request.getUserId());
    }

    @GetMapping("/remain")
    public Boolean remain() {
        return miningService.remain();
    }
}
