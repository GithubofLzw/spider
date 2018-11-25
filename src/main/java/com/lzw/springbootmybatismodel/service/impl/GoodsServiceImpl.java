package com.lzw.springbootmybatismodel.service.impl;

import com.lzw.springbootmybatismodel.domain.Goods;
import com.lzw.springbootmybatismodel.mapper.GoodsMapper;
import com.lzw.springbootmybatismodel.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<Goods> goodsSelect() {
        return goodsMapper.selectAllGoods();
    }

    @Override
    public boolean goodsInsert(List<Goods> goodsList) {
        return goodsMapper.insertGoodsList(goodsList) == 0 ? false : true;
    }
}
