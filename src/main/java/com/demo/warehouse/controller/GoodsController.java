package com.demo.warehouse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.warehouse.entity.Goods;
import com.demo.warehouse.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public IPage<Goods> list(@RequestParam(defaultValue = "1") long page,
                             @RequestParam(defaultValue = "10") long size) {
        return goodsService.page(new Page<>(page, size),
                new QueryWrapper<Goods>().orderByDesc("id"));
    }

    @PostMapping
    public boolean add(@RequestBody Goods goods) {
        return goodsService.save(goods);
    }
}
