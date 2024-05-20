package org.wgh.handshop.service.product;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.util.ImageUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class RankService {
    @Autowired
    private CommodityMapper commodityMapper;

    public JSONObject getPad() throws IOException {
        JSONObject result = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", "安卓平板");
        queryWrapper.eq("commstatus", 1);
        queryWrapper.last("LIMIT 5");
        List<Commodity> list = commodityMapper.selectList(queryWrapper);
        for (Commodity commodity : list) {
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
        }
        QueryWrapper<Commodity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("category", "苹果平板");
        queryWrapper1.eq("commstatus", 1);
        queryWrapper.last("LIMIT 2");
        List<Commodity> list1 = commodityMapper.selectList(queryWrapper1);
        for (Commodity commodity : list1) {
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
        }
        list.add(list1.get(0));
        list.add(list1.get(1));
        result.put("msg", "成功");
        result.put("data", list);
        return result;
    }

    public JSONObject getAndroid() throws IOException {
        JSONObject result = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("category", "安卓");
        queryWrapper.eq("commstatus", 1);
        queryWrapper.last("LIMIT 7");
        List<Commodity> list = commodityMapper.selectList(queryWrapper);
        for (Commodity commodity : list) {
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
        }
        result.put("msg", "成功");
        result.put("data", list);
        return result;
    }

    public JSONObject getiPhone() {
        JSONObject result = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("category", "苹果");
        queryWrapper.eq("commstatus", 1);
        queryWrapper.last("LIMIT 7");
        List<Commodity> list = commodityMapper.selectList(queryWrapper);
        result.put("data", list);
        return result;
    }
}
