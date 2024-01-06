package com.p.order.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
@TableName("`item_detail`")
public class ItemDetail {
    private String id;
    private String batchNo;
    // sku number, part no
    private String productNo;
    private String imei;
    private String sn;
    private String orderNo;
    private String cartoonNo;
    private String boxTotal;
    private String model;
    private String color;
    private String capacity;
    private Date createDate;

    @TableField(exist = false)
    private Integer qty;
    public String getLabel() {
        return model + color + capacity;
    }

}
