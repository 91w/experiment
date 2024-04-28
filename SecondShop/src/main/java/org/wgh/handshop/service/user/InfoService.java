package org.wgh.handshop.service.user;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService {
    public Map<String, String> getInfo() {
        // 授权成功，重上下文中将信息提取
//        UsernamePasswordAuthenticationToken authenticationToken =
//                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();
//        User user = userDetails.getUser();
//        Map<String , String > map = new HashMap<>();
//        map.put("message", "success");
//        map.put("id", user.getId().toString());
//        map.put("username", user.getUsername());
//        map.put("profile", user.getProfile());
//
//        return map;
        return null;
    }
}
