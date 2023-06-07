package com.experiment.backend.controller;

import com.experiment.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/api/user/test/")
    public Map<String, String> test(@RequestBody Map<String, String> data) {
        return testService.test(Integer.valueOf(data.get("id")));
    }
}
