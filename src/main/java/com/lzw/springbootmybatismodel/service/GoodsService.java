package com.lzw.springbootmybatismodel.service;


import com.lzw.springbootmybatismodel.domain.Goods;

import java.util.List;

public interface GoodsService {
    //查询所有商品信息
    List<Goods> goodsSelect();
    //添加多条商品信息
    boolean goodsInsert(List<Goods> goodsList);
}
