package com.demo.warehouse.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("goods")  // 映射数据库表名
public class Goods {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("in_time")
    private LocalDateTime inTime;

    private String type;

    @TableField("out_time")
    private LocalDateTime outTime;

    private String remark;
}
