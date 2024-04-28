package org.wgh.handshop.controller.product;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.product.CarouselService;
import redis.clients.jedis.Response;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/api/carousels")
    public JSONObject getCarousel() {
        return carouselService.getCarousel();
    }

    @RequestMapping("/api/carousels/{imageName}")
    public ResponseEntity<Resource> getImg(@PathVariable String imageName) throws MalformedURLException {
        Path filePath = Paths.get("src/main/resources/static", imageName);
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
