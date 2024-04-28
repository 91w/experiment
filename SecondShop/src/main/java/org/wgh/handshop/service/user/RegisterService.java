package org.wgh.handshop.service.user;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.user.UserInfo;
import org.wgh.handshop.mapper.UserInfoMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterService {
    @Autowired
    private UserInfoMapper userMapper;

    public Map<String, String> register(String username, String password, String phone, String email) {
        Map<String, String> map = new HashMap<>();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        List<UserInfo> list = userMapper.selectList(queryWrapper);
        if (!list.isEmpty()) {
            // 非空，有重名
            map.put("msg", "用户名已存在");
            return map;
        }
        QueryWrapper<UserInfo> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("mobilephone", phone);
        List<UserInfo> list1 = userMapper.selectList(queryWrapper1);
        System.out.println(list1.toString());
        if(!list1.isEmpty()) {
            map.put("msg", "电话号已经被注册过啦");
            return map;
        }

        String avatar = "userimage.jpg";
        password = SaSecureUtil.md5("salt" + password); // 加盐
        UserInfo userInfo = new UserInfo(null, username, password, phone, email, avatar, "女", "北京",
                1, null, null, "这个人很懒什么也没有留下。。。");
        // id自增，传入null即可
        userMapper.insert(userInfo);
        map.put("msg", "成功");
        return map;
    }
}
