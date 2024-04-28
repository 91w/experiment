package org.wgh.handshop.controller.product;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.product.RankService;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/api/padrank")
    public JSONObject getPad() {
        return rankService.getPad();

    }

    @GetMapping("/api/androidrank")
    public JSONObject getAndroid() {
        return rankService.getAndroid();

    }
}
