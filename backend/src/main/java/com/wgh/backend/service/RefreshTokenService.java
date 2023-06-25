package com.wgh.backend.service;

import java.util.Map;

public interface RefreshTokenService {
    Map<String, String> refreshToken(String refreshToken);
}
