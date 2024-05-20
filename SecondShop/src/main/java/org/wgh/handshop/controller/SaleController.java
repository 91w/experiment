package org.wgh.handshop.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.entity.Sale;
import org.wgh.handshop.mapper.SaleMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SaleController {
    @Autowired
    private SaleMapper saleMapper;

    @PostMapping("/api/sale")
    @SaCheckLogin
    public Map<String, String> sale(@RequestBody JSONObject map, @RequestHeader("Satoken") String tokenValue) {
        String result1 = (String) StpUtil.getLoginIdByToken(tokenValue);
        Integer userid = Integer.parseInt(result1);
        String type = (String) map.get("type");
        String used = (String) map.get("used");
        String storage = (String) map.get("storage");
        String channel = (String) map.get("channel");
        String official = (String) map.get("official");
        String battery = (String) map.get("battery");
        String screen = (String) map.get("screen");
        String frame = (String) map.get("frame");
        String accessories = (String) map.get("accessories");
        Object other = map.get("other");
        Sale sale = new Sale(null, userid, type, used, storage, channel, official, battery, screen, frame, accessories, null);
        saleMapper.insert(sale);
        Map<String, String> res = new HashMap<>();
        res.put("msg", "成功");
        return res;
    }
}
