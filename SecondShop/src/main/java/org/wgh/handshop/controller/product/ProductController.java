package org.wgh.handshop.controller.product;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Commimages;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CommimagesMapper;
import org.wgh.handshop.mapper.CommodityMapper;
import org.wgh.handshop.service.product.ProductService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/product")
@SaCheckLogin
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommimagesMapper commimagesMapper;

    @GetMapping("/{id}")
    public JSONObject productDetail(@PathVariable Integer id) {
        return productService.productDetail(id);
    }

    @GetMapping("/category")
    public Map<String, List<String>> getCategory(){
        Map<String, List<String>> map = new HashMap<>();
        String a = "苹果";
        String b = "安卓";
        String c = "平板";
        String d = "苹果平板";
        List<String> list = new LinkedList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        map.put("data", list);
        return map;
    }

    @RequestMapping("/image/{id}") // 获取图片
    public ResponseEntity<List<Map<String, String>>> getImg(@PathVariable Integer id) throws IOException {
        int i = 0;
        QueryWrapper<Commimages> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commid", id);
        List<Commimages> list = commimagesMapper.selectList(queryWrapper);
        List<Map<String, String>> base64Images = new LinkedList<>();
        for(Commimages commimages: list) {
            Path filePath = Paths.get("src/main/resources/static", commimages.getImage());
            byte[] fileContent = Files.readAllBytes(filePath);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            Map<String, String> res = new HashMap<>();
            res.put("id", String.valueOf(i));
            res.put("img", encodedString);
            base64Images.add(res);
            i++;
        }
        return ResponseEntity
                .ok(base64Images);
    }

    @PostMapping("/add")
    public Map<String, String> addProduct(@RequestBody Map<String, String> map) {
        return null;
    }

    @PostMapping("/search")
    public JSONObject search(@RequestBody Map<String, String> map) throws IOException {
        String search = map.get("search");
        System.out.println("search:" + search);
        return productService.search(search);
    }

    @GetMapping("/get")
    public JSONObject getProduct(@RequestParam("category") String category,
                                 @RequestParam("start") Integer start,
                                 @RequestParam("limit") Integer limit  ) throws IOException {
        return productService.getProduct(category, start, limit);
    }
}
