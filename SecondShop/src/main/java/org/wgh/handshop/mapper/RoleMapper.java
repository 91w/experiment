package org.wgh.handshop.mapper;

//import com.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.*;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
}
