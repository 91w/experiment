package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class UserPerm implements Serializable {
    // 权限，定义不同的角色不同的权限

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name; // 新增，删除等操作
    private String type; // 权限类型：0菜单，1操作
    private Integer roleid;

}
