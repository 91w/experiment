package org.wgh.handshop.controller.admin;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.entity.Role;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.RoleMapper;
import org.wgh.handshop.mapper.UserInfoMapper;
import org.wgh.handshop.service.user.LoginService;
import org.wgh.handshop.util.ImageUtil;

import java.util.Map;
@RestController
public class AdminController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleMapper roleMapper;
    @PostMapping("/api/admin/login")
    public JSONObject login(@RequestBody Map<String, String> res) throws Exception{
        JSONObject map = new JSONObject();
        String username = res.get("username");
        String password = res.get("password");
        System.out.println(username + password);
        String nowPassword = SaSecureUtil.md5("salt" + password);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if(userInfo == null) {
            map.put("msg", "用户名错误");
            return map;
        }
        if(userInfo.getPassword().equals(nowPassword)) {
            // 登录成功
            QueryWrapper<Role> queryWrapper1 = new QueryWrapper<Role>();
            queryWrapper1.eq("rolekey", userInfo.getId());
            Role role = roleMapper.selectOne(queryWrapper1);
            if(role.getRolename().equals("管理员")) {
                StpUtil.login(userInfo.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                map.put("msg", "成功");
                map.put("token", tokenInfo.tokenValue);
                userInfo.setPassword(" ");
                userInfo.setUimage("");
                map.put("user", userInfo);
                return map;
            } else {
                map.put("msg", "用户名错误");
                return map;
            }
        }
        return map;
    }
}
