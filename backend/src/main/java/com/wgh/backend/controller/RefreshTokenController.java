package com.wgh.backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RefreshTokenController {

    @GetMapping("/api/refresh")
    public Map<String, String> getToken() {
        return null;
    }
}
