package org.wgh.handshop.controller.order;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.order.Orders;
import org.wgh.handshop.service.OrderService;

import java.io.IOException;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/api/orders")
    @SaCheckLogin
    public Map<String, String> addOrder(@RequestBody Orders orders){
        return null;
    }

    @GetMapping("/api/user/{user_id}/orders")
    @SaCheckLogin
    public JSONObject getOrderById(@PathVariable("user_id") Integer userId,
                                   @RequestParam("start") Integer start,
                                   @RequestParam("limit") Integer limit,
                                    @RequestParam("type") Integer type) throws IOException {
        return orderService.getOrder(userId, type,start, limit);
    }

    @GetMapping("/api/orders/{order_id}")
    @SaCheckLogin
    public JSONObject getOrderById(@PathVariable("order_id") Integer orderid, @RequestParam Integer user_id) throws IOException {
        return orderService.getOrderDetail(orderid, user_id);
    }
}
