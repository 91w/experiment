package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlt
 * @since 2019-12-25
 */
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
@Data
@Accessors(chain = true)//链式写法
public class Reply implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论id
     */
	private Integer cid;

    /**
     * 商品id
     */
	private Integer commid;


    /**
     * 回复内容
     */
	private String recontent;
    /**
     * 回复者id
     */
	private String ruserid;
    /**
     * 回复者昵称
     */
    private String rusername;
    /**
     * 回复者用户头像
     */
    private String ruimage;
    /**
     * 回复时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date replytime;



}
