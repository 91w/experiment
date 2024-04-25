package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    // 购物车, 一个购物车，一个商品，当用户点击自己的购物车时，将userid相等的全部查询
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Integer commid;
    private boolean check; // 是否选择，默认都为false
    private Date createtime;
    private Date updatetime;
}