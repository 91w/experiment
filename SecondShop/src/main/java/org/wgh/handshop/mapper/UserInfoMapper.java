package org.wgh.handshop.mapper;

//import com.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.user.UserInfo;


@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
