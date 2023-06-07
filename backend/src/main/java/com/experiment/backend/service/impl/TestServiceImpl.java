package com.experiment.backend.service.impl;

import com.experiment.backend.mapper.UserMapper;
import com.experiment.backend.pojo.User;
import com.experiment.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> test(Integer id) {
        Map<String, String> resp = new HashMap<>();
        User user = userMapper.selectById(id);
        System.out.println(user);
        resp.put("result", String.valueOf(user));
        return resp;
    }
}
