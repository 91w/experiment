package org.wgh.handshop.service;


import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wgh.handshop.entity.AliPayBean;
import org.wgh.handshop.entity.order.Orders;
import org.wgh.handshop.util.AliPayUtil;

import java.math.BigDecimal;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2022-07-06 15:25
 */
@Service
@Transactional
public class PayService {

    @Resource
    private AliPayUtil aliPayUtil;

//    /**
//     * 使用沙箱支付宝支付订单
//     * @param orderDTO
//     * @return
//     */
    public JSONObject aliPayOrder(Orders orders) {
        JSONObject result = new JSONObject();
        BigDecimal totalPrice = new BigDecimal("0.00");
        String orderNo = "111111111";

        return result;
    }



    /**
     * 初始化支付信息
     * @param orderNo
     * @param totalPrice
     * @return
     */
    public JSONObject initAliPay(String orderNo, BigDecimal totalPrice) {
        JSONObject result = new JSONObject();
        // 封装沙箱支付宝支付信息
        AliPayBean alipayBean = new AliPayBean();
        alipayBean.setOut_trade_no(orderNo);
        alipayBean.setSubject("二手市场沙箱支付宝支付");
        alipayBean.setTotal_amount(String.valueOf(totalPrice));
        alipayBean.setBody("欢迎您在二手市场上下单！！");
        String pay = null;
        try {
            pay = aliPayUtil.pay(alipayBean);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 沙箱支付宝成功支付回调接口
     * @param orderDTO
     * @return
     */
    public Boolean aliPayOrderSuccess(Orders orders) 	{
        // 这里可以写一些回调的接口逻辑，比如更新订单状态
        // 因为我们这有一个OrderDTO的入参，我们可以使用这个实体里面的订单号字段来获取详细订单信息
//        return ResponseDTO.successByMsg(true, "支付成功！");
        return true;
    }



}

