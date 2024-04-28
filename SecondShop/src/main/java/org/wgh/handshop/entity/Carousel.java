package org.wgh.handshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carousel {
    private Integer commid;
    private String image;
    private String title;
}
