package com.wgh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wgh.backend.mapper.UserMapper;
import com.wgh.backend.pojo.User;
import com.wgh.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if(username == null) {
            map.put("error", "用户名不能为空");
            return map;
        }
        if(password == null || confirmedPassword == null) {
            map.put("error", "密码不能为空");
            return map;
        }
        if(password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error", "密码不能为空");
            return map;
        }

        username = username.trim(); // 去除首尾的空格，制表符等空
        if(username.length() == 0) {
            map.put("error", "用户名不能为空");
            return map;
        }
        if(username.length() > 100) {
            map.put("error", "用户名长度超标");
            return map;
        }
        if(password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error", "密码长度超标");
            return map;
        }
        if(!password.equals(confirmedPassword)) {
            map.put("error", "两次密码不一致");
            return map;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        List<User> list = userMapper.selectList(queryWrapper);
        if (!list.isEmpty()) {
            // 非空，有重名
            map.put("error", "用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String profile = "https://cdn.acwing.com/media/user/profile/photo/228797_lg_ad14bab6dd.png";
        User user = new User(null, username, encodedPassword, profile, null, null); // id自增，传入null即可
        userMapper.insert(user);

        map.put("message", "success");
        return map;


    }
}
