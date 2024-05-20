package org.wgh.handshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.Address;
import org.wgh.handshop.entity.Collect;

@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {

}
