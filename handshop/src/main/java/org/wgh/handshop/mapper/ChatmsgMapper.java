package org.wgh.handshop.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.*;
import org.wgh.handshop.entity.chat.ChatMsg;

import java.util.List;

@Mapper
@Repository
public interface ChatmsgMapper extends BaseMapper<ChatMsg> {
}