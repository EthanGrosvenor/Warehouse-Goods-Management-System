package com.demo.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.warehouse.entity.Goods;
import com.demo.warehouse.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;   // 从 Spring 容器注入

    @MockBean
    private GoodsMapper goodsMapper;     // 把 Mapper 替换成 Mockito Mock

    @Test
    void page_shouldReturnPagedData() {
        // ① 准备模拟分页对象
        Goods g = new Goods();
        g.setId(1L);
        g.setType("测试");

        Page<Goods> mockPage = new Page<>(1, 10);
        mockPage.setRecords(List.of(g));
        mockPage.setTotal(1);

        // ② 当 GoodsMapper.selectPage(...) 被调用时，返回上面的模拟 Page
        Mockito.when(goodsMapper.selectPage(any(Page.class), any()))
                .thenReturn(mockPage);

        // ③ 调用 Service
        IPage<Goods> result = goodsService.page(new Page<>(1, 10));

        // ④ 断言
        assertThat(result.getTotal()).isEqualTo(1);
        assertThat(result.getRecords().get(0).getType()).isEqualTo("测试");
    }
}
