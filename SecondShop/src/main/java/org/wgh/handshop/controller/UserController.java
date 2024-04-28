package org.wgh.handshop.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Map<String, String> doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        Map<String, String> map = new HashMap<>();
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            map.put("msg", "登录成功");
            map.put("token", tokenInfo.tokenValue);
            return map;
        }
        map.put("msg" , "登录失败");
        return map;
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}

