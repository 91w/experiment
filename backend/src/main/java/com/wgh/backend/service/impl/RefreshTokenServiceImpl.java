package com.wgh.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wgh.backend.config.utils.JwtUtil;
import com.wgh.backend.mapper.UserMapper;
import com.wgh.backend.pojo.User;
import com.wgh.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        if(JwtUtil.validateToken(refreshToken)) {
            // token 有效 且没有过期
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",JwtUtil.extractUsername(refreshToken));
            User user = userMapper.selectOne(queryWrapper);
            String newAccessToken = JwtUtil.generateAccessToken(user);
            String newRefreshToken = JwtUtil.generateRefreshToken(user);
            Map<String, String> map = new HashMap<>();
            map.put("access_token", newAccessToken);
            map.put("refresh_token", newRefreshToken);
            //map.put("expire", String.valueOf(JwtUtil.JWT_ACCESS));
            map.put("msg", "success");
            return map;
        }

        Map<String, String> map = new HashMap<>();
        map.put("msg", "failed");
        System.out.println("应该失败");
        return map;
    }
}
