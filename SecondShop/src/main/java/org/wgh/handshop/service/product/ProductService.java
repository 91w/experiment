package org.wgh.handshop.service.product;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommodityMapper;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CommodityMapper commodityMapper;

    public JSONObject productDetail(Integer commid) {
        JSONObject res = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", commid);
        Commodity commodity = commodityMapper.selectOne(queryWrapper);
        res.put("data", commodity);
        return res;
    }

    public JSONObject search(String search) {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        JSONObject json = new JSONObject();
        queryWrapper.like("category" , search);
        List<Commodity> result = commodityMapper.selectList(queryWrapper);
        json.put("data", result);
        return json;
    }
}
