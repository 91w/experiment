package org.wgh.handshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.user.InfoService;

import java.util.Map;
@RestController
public class UserInfoController {


        @Autowired
        private InfoService infoService;

        @RequestMapping("/api/user/account/info/")
        public Map<String, String> getInfo() {
            return infoService.getInfo();
        }

}
