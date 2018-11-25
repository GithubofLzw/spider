package com.lzw.springbootmybatismodel.controller;

import com.lzw.springbootmybatismodel.domain.Goods;
import com.lzw.springbootmybatismodel.service.GoodsService;
import com.lzw.springbootmybatismodel.spider.GoodsSpiderPipeline;
import com.lzw.springbootmybatismodel.spider.GoodsSpiderProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {


    @Autowired//抓取数据
    private GoodsSpiderProcess goodsSpiderProcess;
    @Autowired//向数据库中添加数据
    private GoodsSpiderPipeline goodsSpiderPipeline;


    //爬虫向商品数据库中添加信息
    @PostMapping("/goods")
    public String goodsInsert() {
        new Spider(goodsSpiderProcess).addUrl("http://search.yhd.com/c0-0-1005412/").addPipeline(goodsSpiderPipeline).thread(6).run();
        return "数据收集结束";
    }

    @Autowired
    private GoodsService goodsService;
    //从数据库中获取信息并展示
    @GetMapping("/goods")
    public List<Goods> goodsList() {
        return goodsService.goodsSelect();
    }
}
