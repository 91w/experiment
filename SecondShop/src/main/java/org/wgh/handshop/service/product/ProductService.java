package org.wgh.handshop.service.product;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public JSONObject search(String search) throws IOException {
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        JSONObject json = new JSONObject();
        queryWrapper.like("category" , search);
        List<Commodity> result = commodityMapper.selectList(queryWrapper);
        for (Commodity commodity: result)
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
        json.put("data", result);
        return json;
    }

    public JSONObject getProduct(String category, Integer start, Integer limit) throws IOException {
        IPage<Commodity> recordIPage = new Page<>(start, limit);//参数：传第几页，每一页有几个参数
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        List<Commodity> list = null;
        long count = 0;
        if("全部".equals(category) || "0".equals(category)) {
            queryWrapper.eq("category", "苹果").
                    or().eq("category", "安卓平板").or()
                    .eq("category", "苹果平板")
                            .or().like("category", "安卓");
            list = commodityMapper.selectPage(recordIPage, queryWrapper).getRecords();
            count = commodityMapper.selectCount(queryWrapper);
        }
        else if("安卓".equals(category)) {
            queryWrapper.like("category", category);
            queryWrapper.notLike("category", "安卓华为");
            list = commodityMapper.selectPage(recordIPage, queryWrapper).getRecords();
        } else {
            queryWrapper.eq("category", category);
            list = commodityMapper.selectPage(recordIPage, queryWrapper).getRecords();
        }
        for(Commodity commodity : list) {
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
            if(commodity.getCategory().equals("苹果")) {

            } else if(commodity.getCategory().equals("平板")){

            }else if(commodity.getCategory().equals("苹果平板")){

            }else {
                commodity.setCategory("安卓");
            }
        }
        JSONObject json = new JSONObject();
        json.put("page", count/limit);
        json.put("msg", "成功");
        json.put("num", list.size());
        json.put("data", list);
        return json;
    }
}
