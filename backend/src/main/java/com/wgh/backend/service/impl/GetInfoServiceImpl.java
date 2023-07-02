package com.wgh.backend.service.impl;

import com.wgh.backend.pojo.User;
import com.wgh.backend.service.GetInfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetInfoServiceImpl implements GetInfoService {
    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User loginUser = (User) authenticationToken.getPrincipal();
        Map<String, String> map = new HashMap<>();
        map.put("msg", "success");
        map.put("id", loginUser.getId().toString());
        map.put("email", loginUser.getEmail());
        map.put("slogan", loginUser.getSlogan());
        map.put("username", loginUser.getUsername());
        map.put("profile", loginUser.getProfile());
        return map;


    }
}
