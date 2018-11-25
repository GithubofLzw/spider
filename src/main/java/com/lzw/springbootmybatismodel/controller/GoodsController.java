package com.lzw.springbootmybatismodel.controller;

import com.lzw.springbootmybatismodel.domain.Goods;
import com.lzw.springbootmybatismodel.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //从数据库中获取信息并展示
    @GetMapping("/goods")
    public List<Goods> goodsList(){
        return goodsService.goodsSelect();
    }

    //爬虫向商品数据库中添加信息
    @PostMapping("/goods")
    public Boolean goodsInsert(){
        List<Goods> goodsList = new ArrayList<>();
        System.out.println("");
        return goodsService.goodsInsert(goodsList);
    }

}
