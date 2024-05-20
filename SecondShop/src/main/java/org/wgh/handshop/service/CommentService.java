package org.wgh.handshop.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Comment;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.CommentMapper;
import org.wgh.handshop.mapper.UserInfoMapper;
import org.wgh.handshop.util.ImageUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Map<String, String> deleteComment(Integer id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        commentMapper.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "成功");
        return map;
    }

    public Map<String, String> addComment(Integer commid, String content, Integer userid) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        UserInfo userInfo = userInfoMapper.selectById(userid);
        Comment comment = new Comment(null, commid, userid, userInfo.getUsername(), userInfo.getUimage(),content, null);
        commentMapper.insert(comment);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "成功");
        return map;
    }

    public JSONObject getComment(Integer commid) throws IOException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.eq("commid", commid);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        for(Comment comment: list) {
            comment.setUserimage(ImageUtil.trimImage(comment.getUserimage()));
        }
        JSONObject jo = new JSONObject();
        jo.put("data", list);
        return jo;
    }
}
