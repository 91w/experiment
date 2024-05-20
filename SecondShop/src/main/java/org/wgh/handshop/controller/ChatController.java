package org.wgh.handshop.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Msg;
import org.wgh.handshop.mapper.MsgMapper;

import java.net.URLDecoder;
import java.util.List;

@RestController
public class ChatController {

    @Resource
    private OpenAiChatClient client; // 注入官方的客户端

    @Resource
    private MsgMapper msgMapper;

    @RequestMapping("/api/chat")
    @SaCheckLogin
    public JSONObject chat(@RequestBody String question, @RequestHeader ("Satoken") String tokenValue) {
        String result1 = (String)StpUtil.getLoginIdByToken(tokenValue);
        Integer userid = Integer.parseInt(result1);
        question = URLDecoder.decode(question).split("=")[0];
        String res = client.call(question);
        JSONObject result = new JSONObject();
        Msg msg = new Msg(null, userid, res, question, null);
        msgMapper.insert(msg);
        result.put("answer", res);
        return result;
    }

    @GetMapping("/api/chat/history")
    @SaCheckLogin
    public JSONObject getHistory(@RequestHeader ("Satoken") String tokenValue) {
        String result1 = (String)StpUtil.getLoginIdByToken(tokenValue);
        Integer userid = Integer.parseInt(result1);
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<Msg>();
        queryWrapper.eq("userid", userid);
        List<Msg> list = msgMapper.selectList(queryWrapper);
        JSONObject result = new JSONObject();
        result.put("data", list);
        result.put("msg", "成功");
        return result;
    }

}
