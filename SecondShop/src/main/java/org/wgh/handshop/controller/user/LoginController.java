package org.wgh.handshop.controller.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.service.user.LoginService;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService; //使用接口

    @PostMapping("/api/user/login")
    public JSONObject login(@RequestBody Map<String, String> res) throws Exception{
        // 这个username，也可能是手机号登录
        if(res == null || res.isEmpty())
            System.out.println("空值");
        else System.out.println("不知道有什么");
        String username = res.get("username");
        String password = res.get("password");  // 将请求中的参数放到一个字典中
        System.out.println("controller:" + username + "密码:" + password);
        return loginService.login(username, password);
    }

//    @RequestMapping("/api/ping")
//    public JSONObject ping(@RequestParam Map<String, String> map){
//        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//        System.out.println(tokenInfo);
//        return null;
//
//    }
}
