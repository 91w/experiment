package org.wgh.handshop.controller.admin;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Sale;
import org.wgh.handshop.mapper.SaleMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController extends ServiceImpl<SaleMapper, Sale> {
    @Autowired
    private SaleMapper saleMapper;

    private JSONObject res = new JSONObject();
    @GetMapping("/api/admin/sale")
    public JSONObject getAllGoods() {
        List<Sale> list = this.list();
        for(Sale sale: list) {
            if(sale.getUsed().equals("1")) {
                sale.setUsed("正常使用");
            } else if(sale.getUsed().equals("2")) sale.setUsed("无法使用");
            else sale.setUsed("未拆封");

            if(sale.getChannel().equals("1")) sale.setChannel("大陆 ");
            else if(sale.getChannel().equals("2")) sale.setChannel("港澳台");
            else sale.setChannel("海外");

            if(sale.getOfficial().equals("1")) sale.setOfficial("在保");
            else sale.setChannel("过保");

            if(sale.getBattery().equals("1")) sale.setBattery("电池健康");
            else sale.setBattery("不健康");

            if(sale.getScreen().equals("1")) sale.setScreen("完美无划痕");
            else if(sale.getScreen().equals("2")) sale.setScreen("细微划痕");
            else sale.setScreen("屏幕破损");

            if(sale.getFrame().equals("1")) sale.setFrame("完美无划痕");
            else if(sale.getFrame().equals("2")) sale.setFrame("细微划痕");
            else sale.setFrame("裂痕");

            if(sale.getAccessories().equals("1")) sale.setAccessories("配件包装齐全");
            else sale.setAccessories("配件/包装不齐全");
        }
        res.put("msg", "成功");
        res.put("data", list);
        return res;
    }

    @DeleteMapping("/api/admin/sale")
    public JSONObject deleteGoods(@RequestParam("id") Integer id) {
        saleMapper.deleteById(id);
        res.put("msg", "成功");
        return res;
    }

    @PostMapping("/api/admin/sale")
    public JSONObject saveGoods(@RequestParam("id") Integer id) {
        Sale sale = saleMapper.selectById(id);
        saleMapper.deleteById(id);
        res.put("msg", "成功");
        return res;
    }
}
