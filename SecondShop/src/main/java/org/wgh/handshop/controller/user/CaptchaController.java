package org.wgh.handshop.controller.user;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CaptchaController {
    @Resource
    private DefaultKaptcha captchaProducer;

    @GetMapping("/api/captcha")
    public Map<String, String> Code() throws IOException {
        // 生成文字验证码
        String text = captchaProducer.createText();
        // 生成图片验证码
        ByteArrayOutputStream out = null;
        BufferedImage image = captchaProducer.createImage(text);
        out=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",out);
        // 对字节组Base64编码
        Map<String, String> map = new HashMap<>();
        map.put("code", text);
        map.put("img", Base64.getEncoder().encodeToString(out.toByteArray()));
        return map;
    }

}
