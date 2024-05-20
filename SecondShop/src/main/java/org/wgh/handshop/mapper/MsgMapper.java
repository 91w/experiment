package org.wgh.handshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.Cart;
import org.wgh.handshop.entity.Msg;

@Mapper
@Repository
public interface MsgMapper extends BaseMapper<Msg> {

}
