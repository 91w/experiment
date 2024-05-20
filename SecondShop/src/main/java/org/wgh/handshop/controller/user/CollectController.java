package org.wgh.handshop.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.service.user.CollectService;

import java.io.IOException;
import java.util.Map;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;
    @PostMapping("/api/collect")
    @SaCheckLogin
    public Map<String, String> addCollect(@RequestBody Map<String, String> map) {
        Integer userid = Integer.valueOf(map.get("user_id"));
        Integer commid = Integer.valueOf(map.get("product_id"));
        return collectService.addCollects(userid, commid);
    }

    @GetMapping("/api/collect/{user_id}")
    @SaCheckLogin
    public JSONObject getCollect(@PathVariable Integer user_id,
                                 @RequestParam Integer start,
                                 @RequestParam Integer limit) throws IOException {
        Integer userid = user_id;
        return collectService.getCollect(userid, start, limit);

    }

    @DeleteMapping("/api/collect")
    @SaCheckLogin
    public Map<String, String> deleteCollect() {
        return null;
    }
}
