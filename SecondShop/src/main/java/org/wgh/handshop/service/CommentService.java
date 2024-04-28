package org.wgh.handshop.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Comment;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.CommentMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public Map<String, String> deleteComment(Integer id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        commentMapper.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "成功");
        return map;
    }

    public JSONObject getComment(Integer commid) throws IOException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.eq("commid", commid);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        for(Comment comment: list) {
            Path filePath = Paths.get("src/main/resources/static", comment.getUserimage());
            byte[] fileContent = Files.readAllBytes(filePath);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            comment.setUserimage(encodedString);
        }
        JSONObject jo = new JSONObject();
        jo.put("data", list);
        return jo;
    }
}
