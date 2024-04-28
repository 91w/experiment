package org.wgh.handshop.service.product;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommodityMapper;

import java.util.List;

@Service
public class RankService {
    @Autowired
    private CommodityMapper commodityMapper;

    public JSONObject getPad() {
        JSONObject result = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", "平板");
        queryWrapper.eq("commstatus", 1);
        queryWrapper.last("LIMIT 5");
        List<Commodity> list = commodityMapper.selectList(queryWrapper);
        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("category", "苹果平板");
        queryWrapper1.eq("commstatus", 1);
        queryWrapper.last("LIMIT 2");
        List<Commodity> list1 = commodityMapper.selectList(queryWrapper1);
        list.add(list1.get(0));
        list.add(list1.get(1));
        result.put("data", list);
        System.out.println(list.size());
        System.out.println(result);
        return result;
    }

    public JSONObject getAndroid() {
        JSONObject result = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("category", "安卓");
        queryWrapper.eq("commstatus", 1);
        queryWrapper.last("LIMIT 7");
        List<Commodity> list = commodityMapper.selectList(queryWrapper);
        System.out.println(list.size());
        result.put("data", list);
        System.out.println(result);
        return result;
    }
}
