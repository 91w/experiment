package org.wgh.handshop.controller.Cart;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.service.CartService;

import java.io.IOException;
import java.util.Map;

@RestController
public class CartController {
    @Resource
    private CartService cartService;

    @PostMapping("/api/carts")
    @SaCheckLogin
    public Map<String, String> createCart(@RequestBody Map<String, String> map) {
        Integer commid = Integer.valueOf(map.get("product_id"));
        Integer userid = Integer.valueOf(map.get("user_id"));
        return cartService.addCart(commid, userid);
    }

    @GetMapping("/api/carts/{user_id}")
    @SaCheckLogin
    public JSONObject getCart(@PathVariable Integer user_id) throws IOException {
        return cartService.getCart(user_id);
    }

    @PutMapping("/api/carts")
    @SaCheckLogin
    public String updateCart(){
        return null;
    }

    @DeleteMapping("/api/carts")
    @SaCheckLogin
    public Map<String, String> deleteCart(@RequestBody Map<String, String> map){
        Integer commid = Integer.valueOf(map.get("product_id"));
        Integer userid = Integer.valueOf(map.get("user_id"));
        return cartService.deleteCart(commid, userid);
    }
}
