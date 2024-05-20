package org.wgh.handshop.controller.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.UserInfoMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChangeController {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @RequestMapping("/api/user/changePass")
    @SaCheckLogin
    public Map<String, String> changePass(@RequestBody Map<String, String> map, @RequestHeader("Satoken") String tokenValue) {
        String password = map.get("password");
//        String password_confirm = map.get("password_confirm");
        String nowPassword = SaSecureUtil.md5("salt" + password);
        String result1 = (String) StpUtil.getLoginIdByToken(tokenValue);
        Integer userid = Integer.parseInt(result1);
        UserInfo user = userInfoMapper.selectById(userid);
        user.setPassword(password);
        userInfoMapper.updateById(user);
        Map<String, String> res = new HashMap<>();
        res.put("msg", "成功");
        return res;
    }
}
