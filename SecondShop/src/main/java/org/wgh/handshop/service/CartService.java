package org.wgh.handshop.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Cart;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CartMapper;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.util.ImageUtil;
import org.wgh.handshop.vo.CartVo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    public JSONObject getCart(Integer userid) throws IOException {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        List<Cart> list = cartMapper.selectList(queryWrapper);
        JSONObject jsonObject = new JSONObject();
        List<CartVo> result = new LinkedList<>();
        for(Cart cart: list) {
            CartVo cartVo = new CartVo();
            QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
            Commodity commodity = commodityMapper.selectById(cart.getCommid());
            cartVo.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
            cartVo.setId(commodity.getId());
            cartVo.setCommdesc(commodity.getCommdesc());
            cartVo.setThinkmoney(commodity.getThinkmoney());
            cartVo.setStatus(cart.getStatus() == 1);
            result.add(cartVo);
        }
        jsonObject.put("sum", list.size());
        jsonObject.put("msg", "成功");
        jsonObject.put("data", result);
        return jsonObject;
    }

    public Map<String, String> deleteCart(Integer commid, Integer userid) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("commid", commid);
        cartMapper.delete(queryWrapper);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "成功");
        return map;
    }

    public Map<String, String> addCart(Integer commid, Integer userid) {
        Map<String, String> map = new HashMap<>();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("commid", commid);
        List<Cart> list = cartMapper.selectList(queryWrapper);
        if(list == null || list.isEmpty()) {
            // 没有，可以加入
            Cart cart = new Cart(null,userid, commid, null, null, 1);
            cartMapper.insert(cart);
            map.put("status", "成功");
            return map;
        } else {
            map.put("status", "失败");
            map.put("msg", "商品已经在购物车了，看看其他的吧");
            return map;
        }
    }


}
