package com.p.order.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`stock_number`")
public class StockNumber {

    private String id;
    private String model;
    private String skuNumber;
    private String color;
    private String capacity;
    private String description;
}
