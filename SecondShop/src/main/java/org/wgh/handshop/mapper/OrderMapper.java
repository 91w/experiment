package org.wgh.handshop.mapper;

//import com.entity.Soldrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.order.Order;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
