package com.wgh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wgh.backend.config.utils.JwtUtil;
import com.wgh.backend.mapper.UserMapper;
import com.wgh.backend.pojo.User;
import com.wgh.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        User loginUser = (User) authenticate.getPrincipal();
        // token 是用用户名 生成
        String accessToken = JwtUtil.generateAccessToken(loginUser);
        String refreshToken = JwtUtil.generateRefreshToken(loginUser);
        map.put("username", loginUser.getUsername());
        map.put("msg", "success");
        map.put("access_token", accessToken);
        map.put("refresh_token", refreshToken);
        map.put("profile", loginUser.getProfile());
        return map;

    }
}
