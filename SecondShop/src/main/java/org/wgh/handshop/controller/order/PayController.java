package org.wgh.handshop.controller.order;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.entity.order.Orders;
import org.wgh.handshop.service.PayService;

@RestController
@RequestMapping("/store")
public class PayController {

    @Resource
    private PayService payService;


    /**
     * 使用沙箱支付宝支付订单
//     * @param orderDTO
     * @return
     */
    @PostMapping("/order/aliPay")
    public JSONObject aliPayOrder(@RequestBody Orders orders){
        return payService.aliPayOrder(orders);
    }

    /**
     * 沙箱支付宝成功支付回调接口
//     * @param orderDTO
     * @return
     */
    @PostMapping("/order/aliPay/success")
    public boolean aliPayOrderSuccess(@RequestBody Orders orders){
        return payService.aliPayOrderSuccess(orders);
    }


}
