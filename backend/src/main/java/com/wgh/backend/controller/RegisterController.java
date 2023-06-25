package com.wgh.backend.controller;

import com.wgh.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/api/account/register")
    public Map<String, String> register(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String confirmedPassword = data.get("confirm_password");
        return registerService.register(username, password, confirmedPassword);
    }
}
