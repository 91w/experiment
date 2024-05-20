package org.wgh.handshop.service.user;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Collect;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CollectMapper;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.util.ImageUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    public Map<String, String> addCollects(Integer userid, Integer commid) {
        HashMap<String, String> map = new HashMap<>();
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("commid", commid);
        List<Collect> list = collectMapper.selectList(queryWrapper);
        if(!list.isEmpty()) {
            map.put("msg", "已经存在了，喜欢就购买吧");
            return map;
        }
        Collect collect = new Collect(null, commid, userid, null, 1);
        collectMapper.insert(collect);
        map.put("msg", "成功");
        return map;
    }

    public JSONObject getCollect(Integer userid, Integer start, Integer limit) throws IOException {
        IPage<Collect> recordIPage = new Page<>(start, limit);//参数：传第几页，每一页有几个参数
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        //排序顺序：认为自定义，按照时间最新顺序
        queryWrapper.eq("userid" , userid);
        List<Collect> records = collectMapper.selectPage(recordIPage, queryWrapper).getRecords();
        JSONObject json = new JSONObject();
        List<Commodity> res = new LinkedList<>();
        for(Collect c : records) {
            Commodity commodity = commodityMapper.selectById(c.getCommid());
            commodity.setMainimg(ImageUtil.trimImage(commodity.getMainimg()));
            res.add(commodity);
        }
        json.put("data", res);
        json.put("total", records.size());
        json.put("msg", "成功");
        return json;
    }

    public Map<String, String> deleteCollects(Integer userid, Integer commid) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("commid", commid);
        collectMapper.delete(queryWrapper);
        HashMap<String, String> map = new HashMap<>();
        map.put("mag", "成功");
        return map;
    }
}
