package org.wgh.handshop.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Sale;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.RoleMapper;
import org.wgh.handshop.mapper.SaleMapper;
import org.wgh.handshop.mapper.UserInfoMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController extends ServiceImpl<UserInfoMapper, UserInfo> {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleMapper roleMapper;
    private JSONObject res = new JSONObject();
    @PostMapping("/api/admin/user")
    public JSONObject kickUser(@RequestParam("id") Integer id) {
        StpUtil.logout(id);
        res.put("msg", "成功");
        return res;
    }

    @DeleteMapping("/api/admin/user")
    public JSONObject deleteUser(@RequestParam("id") Integer id) {
        userInfoMapper.deleteById(id);
        res.put("msg", "成功");
        return res;
    }

    @GetMapping("/api/admin/user")
    public JSONObject getUser() {
        List<UserInfo> list = this.list();
        List<UserInfo> filteredList = list.stream()
                .filter(userInfo -> roleMapper.selectById(userInfo.getId()).getRolename().equals("管理员"))
                .collect(Collectors.toList());
        res.put("msg", "成功");
        res.put("data", filteredList);
        return res;
    }
}
