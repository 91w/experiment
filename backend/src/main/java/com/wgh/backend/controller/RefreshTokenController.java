package com.wgh.backend.controller;


import com.wgh.backend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/api/refresh")
    public Map<String, String> getToken(@RequestBody Map<String, String> data) {
        String refreshToken = data.get("refresh_token");
        return refreshTokenService.refreshToken(refreshToken);
    }
}
