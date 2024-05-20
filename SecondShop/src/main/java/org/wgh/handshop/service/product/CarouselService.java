package org.wgh.handshop.service.product;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Carousel;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommodityMapper;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarouselService {

    private String imgPath;
    @Autowired
    private CommodityMapper commodityMapper;

    public JSONObject getCarousel() {
        // 人为的设置几个轮播图展示
        Carousel carousel1 = null;
        Carousel carousel2 = null;
        Carousel carousel3 = null;
        JSONObject jo = new JSONObject();
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper();
        Commodity commodity1 = commodityMapper.selectById(1);
        Commodity commodity2 = commodityMapper.selectById(2);
        Commodity commodity3 = commodityMapper.selectById(3);
        carousel1 = new Carousel(1, commodity1.getMainimg(), commodity1.getCommname());
        carousel2 = new Carousel(2, commodity2.getMainimg(), commodity2.getCommname());
        carousel3 = new Carousel(3, commodity3.getMainimg(), commodity3.getCommname());
        jo.put("msg", "成功");
        List<Carousel> list = new LinkedList<>();
        list.add(carousel1);
        list.add(carousel2);
        list.add(carousel3);
        jo.put("data", list);
        return jo;
    }
}
