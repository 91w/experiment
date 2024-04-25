package org.wgh.handshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.UserPerm;

import java.util.List;


@Mapper
@Repository
public interface UserPermMapper extends BaseMapper<UserPerm> {
}
