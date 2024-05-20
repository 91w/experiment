package org.wgh.handshop.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartVo {
    private Integer id;
    private String commdesc;
    private BigDecimal thinkmoney;
    private String mainimg;
    private boolean status;

}
