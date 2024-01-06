package com.p.order.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`conf`")
public class Conf {

    private String id;
    private String item;
    private String value;
}
