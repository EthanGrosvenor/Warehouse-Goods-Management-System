package com.demo.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
