package org.wgh.handshop.service.user;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.UserInfoMapper;
import org.wgh.handshop.util.ImageUtil;
import org.wgh.handshop.util.JustPhone;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    public JSONObject login(String username, String password) throws Exception{
        JSONObject map = new JSONObject();
        //判断输入的账号是否手机号
        if (!JustPhone.justPhone(username)) {
            //输入的是用户名
            //盐加密
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
                StpUtil.login(userInfo.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                map.put("msg", "成功");
                map.put("token", tokenInfo.tokenValue);
                userInfo.setPassword(" ");
                userInfo.setUimage(ImageUtil.trimImage(userInfo.getUimage()));
                map.put("user", userInfo);
                return map;
            }else {
                map.put("msg", "密码错误");
                return map;
            }
        }else {
            //输入的是手机号
            String phone = username;
            String nowPassword = SaSecureUtil.md5("salt" + password);
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
            queryWrapper.eq("mobilephone", phone);
            UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
            if(userInfo.getPassword().equals(nowPassword)) {
                // 登录成功
                StpUtil.login(userInfo.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                map.put("msg", "成功");
                map.put("token", tokenInfo.tokenValue);
                userInfo.setPassword(" ");
                userInfo.setUimage(ImageUtil.trimImage(userInfo.getUimage()));
                map.put("user", userInfo);
                return map;
            }else {
                map.put("msg", "密码错误");
                return map;
            }
        }
    }
}
