package org.wgh.handshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.user.RegisterService;

import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/api/user/register")
    public Map<String, String> register(@RequestBody Map<String, String> map) {
        return registerService.register(map.get("username"), map.get("password"), map.get("phone"), map.get("email"));
    }
}
