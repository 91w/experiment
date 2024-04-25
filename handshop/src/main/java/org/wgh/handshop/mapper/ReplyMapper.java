package org.wgh.handshop.mapper;

//import com.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.*;
import java.util.List;


@Mapper
@Repository
public interface ReplyMapper extends BaseMapper<Reply> {

}
