package com.htzw.mongodb.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/7 15:45
 */
@Data
@ToString
@Accessors(chain = true)
public class Status {

    private Integer weight;
    private Integer height;

}
