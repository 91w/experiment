package org.wgh.handshop.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.util.ImageUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RestController
public class MainController {
    // 获取主页的几个推荐图片
    @Autowired
    private CommodityMapper commodityMapper;
    @GetMapping("/api/test")
    public JSONObject test() throws IOException {
        List<Integer> ids = new LinkedList<>();
        Collections.addAll(ids, 33,38, 40, 41, 44, 45);
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        List<Commodity> list = commodityMapper.selectBatchIds(ids);
        List<String> res = new LinkedList<>();
        for(Commodity commodity: list) {
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
            res.add(commodity.getMainimg());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", res);
        return jsonObject;
    }
}
