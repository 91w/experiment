package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class Commodity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer commid;
    /**
     * 商品名
     */
    private String commname;
    /**
     * 商品描述
     */
    private String commdesc;
    /**
     * 视频
     */
    private String videourl;

    /**
     * 原价
     */
    private BigDecimal orimoney;
    /**
     * 售价
     */
    private BigDecimal thinkmoney;

    /**
     * 商品所在地区，用于筛选 -- 仅仅 省
     */
    private String province;

    /**
     * 发布时间
     */
    private Date createtime;
    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 0违规 1正常 2删除  3待审核
     */
    private Integer commstatus;
    /**
     * 常用选项：自提，可小刀，不议价等选项，全场包邮，7天无理由等显示
     */
    private String common;

    /**
     * 商品类别，筛选时进行分类
     */
    private String category;

}