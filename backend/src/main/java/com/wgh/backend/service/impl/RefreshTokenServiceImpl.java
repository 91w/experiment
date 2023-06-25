package com.wgh.backend.service.impl;

import com.wgh.backend.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        return null;
    }
}
