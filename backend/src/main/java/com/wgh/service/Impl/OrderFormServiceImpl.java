package com.wgh.service.Impl;

import com.wgh.dao.OrderFormMapper;
import com.wgh.pojo.OrderForm;
import com.wgh.service.OrderFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wsk1103 on 2017/5/13.
 */
@Service
public class OrderFormServiceImpl implements OrderFormService {
    @Resource
    private OrderFormMapper orderFormMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(OrderForm record) {
        return orderFormMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderForm record) {
        return orderFormMapper.insertSelective(record);
    }

    @Override
    public OrderForm selectByPrimaryKey(Integer id) {
        return orderFormMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderForm record) {
        return orderFormMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderForm record) {
        return orderFormMapper.updateByPrimaryKey(record);
    }

    @Override
    public int getCounts(int uid) {
        return orderFormMapper.getCounts(uid);
    }

    @Override
    public List<OrderForm> selectByUid(int uid, int start) {
        return orderFormMapper.selectByUid(uid, start);
    }
}
