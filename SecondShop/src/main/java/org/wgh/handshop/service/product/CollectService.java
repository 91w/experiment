package org.wgh.handshop.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.convert.QueryMapper;
import org.springframework.stereotype.Service;
import org.wgh.handshop.entity.Collect;
import org.wgh.handshop.entity.Commodity;
import org.wgh.handshop.mapper.CollectMapper;

import java.util.Map;

@Service
public class CollectService {
    @Autowired
    private CollectMapper collectMapper;

    public Map<String, String> detail(Integer id) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        return null;
    }
}
