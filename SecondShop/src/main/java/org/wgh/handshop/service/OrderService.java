package org.wgh.handshop.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Address;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.entity.order.Detail;
import org.wgh.handshop.entity.order.Orders;
import org.wgh.handshop.mapper.AddressMapper;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.mapper.DetailMapper;
import org.wgh.handshop.mapper.OrderMapper;
import org.wgh.handshop.util.ImageUtil;
import org.wgh.handshop.vo.OrderVo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private DetailMapper detailMapper;

    public Map<String, String> addOrder(Orders orders) {
        Map<String, String> map = new HashMap<>();
        orderMapper.insert(orders);
        map.put("msg", "成功");
        return map;
    }
    
    public JSONObject getOrder(Integer userid, Integer type, Integer start, Integer limit) throws IOException {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        IPage<Orders> recordIPage = new Page<>(start, limit);//参数：传第几页，每一页有几个参数
        List<List<OrderVo>> result = new LinkedList<>();
        if (type.equals(0)) {
            queryWrapper.eq("userid", userid);
            List<Orders> list = orderMapper.selectPage(recordIPage, queryWrapper).getRecords();
            for(Orders orders : list) {
                QueryWrapper<Detail> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("orderid", orders.getId());
                List<Detail> details = detailMapper.selectList(queryWrapper1);
                List<OrderVo> res = new LinkedList<>();
                for(Detail detail: details) {
                    Commodity commodity = commodityMapper.selectById(detail.getCommid());
                    OrderVo orderVo = new OrderVo();
                    orderVo.setExpiretime(orders.getExpiretime());
                    orderVo.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
                    orderVo.setCommname(commodity.getCommname());
                    orderVo.setCreatetime(orders.getCreatetime());
                    orderVo.setThinkmoney(commodity.getThinkmoney());
                    orderVo.setCommid(commodity.getId());
                    orderVo.setType(orders.getStatus());
                    orderVo.setOrderid(orders.getId());
                    res.add(orderVo);
                }
                result.add(res);
            }
        }else {
            queryWrapper.eq("userid", userid);
            queryWrapper.eq("status", type);
            List<Orders> list = orderMapper.selectPage(recordIPage, queryWrapper).getRecords();
            for(Orders orders : list) {
                QueryWrapper<Detail> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("orderid", orders.getId());
                List<Detail> details = detailMapper.selectList(queryWrapper1);
                List<OrderVo> res = new LinkedList<>();
                for(Detail detail: details) {
                    Commodity commodity = commodityMapper.selectById(detail.getCommid());
                    OrderVo orderVo = new OrderVo();
                    orderVo.setExpiretime(orders.getExpiretime());
                    orderVo.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
                    orderVo.setCommname(commodity.getCommname());
                    orderVo.setCreatetime(orders.getCreatetime());
                    orderVo.setThinkmoney(commodity.getThinkmoney());
                    orderVo.setCommid(commodity.getId());
                    orderVo.setType(orders.getStatus());
                    orderVo.setOrderid(orders.getId());
                    res.add(orderVo);
                }
                result.add(res);
            }
        }
        JSONObject json = new JSONObject();
        json.put("data", result);
        json.put("msg", "成功");
        return json;
    }

    public JSONObject getOrderDetail(Integer orderid, Integer userid) throws IOException {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderid", orderid);
        List<Detail> list = detailMapper.selectList(queryWrapper);
        List<OrderVo> res = new LinkedList<>();
        Orders orders = orderMapper.selectById(orderid);
        BigDecimal total_price = new BigDecimal("0");
        for(Detail detail: list) {
            Commodity commodity = commodityMapper.selectById(detail.getCommid());
            OrderVo orderVo = new OrderVo();
            orderVo.setExpiretime(orders.getExpiretime());
            orderVo.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
            orderVo.setCommname(commodity.getCommname());
            orderVo.setCreatetime(orders.getCreatetime());
            orderVo.setThinkmoney(commodity.getThinkmoney());
            orderVo.setCommid(commodity.getId());
            orderVo.setType(orders.getStatus());
            orderVo.setOrderid(orders.getId());
            orderVo.setPaytime(orders.getPaytime());
            total_price = total_price.add(commodity.getThinkmoney());
            res.add(orderVo);
        }
        JSONObject json = new JSONObject();
        json.put("data", res);
        QueryWrapper<Address> queryWrapper2 = new QueryWrapper<Address>();
        queryWrapper.eq("userid", userid);
        List<Address> addresses = addressMapper.selectList(queryWrapper2);
        json.put("address", addresses.get(0));
        json.put("orderid", orders.getId());
        json.put("total_price", total_price);
        json.put("msg", "成功");
        return json;
    }
}
