package com.wgh.backend.service;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(String username, String password);
}
