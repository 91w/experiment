package org.wgh.handshop.controller.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.service.user.LoginService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService; //使用接口

    @PostMapping("/api/user/login")
    public JSONObject login(@RequestBody Map<String, String> res) throws Exception{
        // 这个username，也可能是手机号登录
        String username = res.get("username");
        String password = res.get("password");
        return loginService.login(username, password);
    }

    @RequestMapping("/api/ping")
    public Map<String, String> ping(){
        if(StpUtil.isLogin()){
            Map<String, String> map = new HashMap<>();
            map.put("msg", "成功");
            return map;
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("msg", "失败");
            return map;
        }
    }
}
