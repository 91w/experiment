package org.wgh.handshop.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgh.handshop.service.CommentService;

import java.io.IOException;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/comment/{commid}")
    public JSONObject getComment(@PathVariable Integer commid) throws IOException {
        return commentService.getComment(commid);
    }

    @RequestMapping("/api/comment/delet/{id}")
    public Map<String, String> delComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }
}
