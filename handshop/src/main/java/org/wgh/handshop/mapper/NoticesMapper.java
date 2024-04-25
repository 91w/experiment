package org.wgh.handshop.mapper;

//import com.entity.Notices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@Repository
public interface NoticesMapper extends BaseMapper<Notices> {
}
