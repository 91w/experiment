package org.wgh.handshop.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.service.CommentService;

import java.io.IOException;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/comment/{commid}")
    @SaCheckLogin
    public JSONObject getComment(@PathVariable Integer commid) throws IOException {
        return commentService.getComment(commid);
    }

    @RequestMapping("/api/comment/delet/{id}")
    @SaCheckLogin
    public Map<String, String> delComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }

    @PostMapping("/api/comment/add")
    @SaCheckLogin
    public Map<String, String> addComment(@RequestBody Map<String, String> map) {
        Integer commid = Integer.valueOf(map.get("commid"));
        String content = map.get("content");
        Integer userid = Integer.valueOf(map.get("user_id"));
        return commentService.addComment(commid, content, userid);
    }
}
