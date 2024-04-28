package org.wgh.handshop.mapper;

//import com.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wgh.handshop.entity.*;
import java.util.List;
import org.wgh.handshop.entity.Comment;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
