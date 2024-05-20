package org.wgh.handshop.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wgh.handshop.entity.Address;
import org.wgh.handshop.mapper.AddressMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressMapper addressMapper;

    @GetMapping("/addresses/{user_id}")
    public Map<String, Object> getAddresses(@PathVariable("user_id") Integer userid){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.eq("userid", userid);
        List<Address> res = addressMapper.selectList(queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", res);
        return map;
    }
    @PostMapping("/addresses")
    public Map<String, Object> addAddress(@RequestBody Map<String, String> map) {
        Integer userid = Integer.valueOf(map.get("user_id"));
        String name = map.get("name");
        String phone = map.get("phone");
        String address = map.get("address");
        Address address1 = new Address(null, userid, name, phone, address, null, null);
        addressMapper.insert(address1);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.eq("userid", userid);
        List<Address> list = addressMapper.selectList(queryWrapper);
        HashMap<String, Object> res = new HashMap<>();
        res.put("msg", "成功");
        res.put("data", list);
        return res;
    }
}
