package com.wgh.backend.controller;

import com.mysql.cj.log.Log;
import com.wgh.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/account/login")
    public Map<String, String> login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        return loginService.login(username, password);
    }
}
