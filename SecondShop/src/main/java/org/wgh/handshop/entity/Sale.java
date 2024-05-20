package org.wgh.handshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userid;
    private String type;
    private String used;
    private String storage;
    private String channel;
    private String official;
    private String battery;
    private String screen;
    private String frame;
    private String accessories;
    private Object other;
}
