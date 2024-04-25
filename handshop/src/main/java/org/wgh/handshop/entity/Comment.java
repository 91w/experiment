package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
@Data
@Accessors(chain = true)//链式写法
public class Comment implements Serializable {
    // 商品的评论

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 商品id
     */
	private Integer commid;
    /**
     * 评论者id
     */
	private Integer userid;
    /**
     * 评论者昵称
     */
	private String username;
    /**
     * 评论者用户头像
     */
    private String userimage;
    /**
     * 评论内容
     */
	private String content;
    /**
     * 评论时间
     */
	private Date commtime;
    /**
     * 评论对应的回复集合
     */
    private List<Reply> replyLsit;
}
