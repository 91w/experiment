package org.wgh.handshop.controller.product;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.product.RankService;

import java.io.IOException;

@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/api/padrank")
    public JSONObject getPad() throws IOException {
        return rankService.getPad();
    }

    @GetMapping("/api/iphonerank")
    public JSONObject getiPhonerank(){
        return rankService.getiPhone();
    }

    @GetMapping("/api/androidrank")
    public JSONObject getAndroid() throws IOException {
        return rankService.getAndroid();

    }
}
