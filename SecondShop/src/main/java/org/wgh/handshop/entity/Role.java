package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
@Data
public class Role {
    // 角色定义，用于控制权限

    @TableId(type = IdType.AUTO)
    private Integer id; // 也作为 role_id

    /**
     * 1普通用户 2管理员 3超级管理员
     */
	private Integer rolekey; // 充当userid
    /**
     * 身份
     */
	private String rolename; // user，admin


}
