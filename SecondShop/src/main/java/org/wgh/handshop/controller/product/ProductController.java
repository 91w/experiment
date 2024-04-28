package org.wgh.handshop.controller.product;

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
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CommimagesMapper commimagesMapper;

    @GetMapping("/{id}")
    public JSONObject productDetail(@PathVariable Integer id) {
        System.out.println("详细"  + id);
        return productService.productDetail(id);
    }

    @RequestMapping("/image/{id}") // 获取图片
    public ResponseEntity<List<Map<String, String>>> getImg(@PathVariable Integer id) throws IOException {
        System.out.println("image_res:" + id);
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
    public JSONObject search(@RequestBody Map<String, String> map) {
        String search = map.get("search");
        System.out.println("search:" + search);
        return productService.search(search);
    }
}
