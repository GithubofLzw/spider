package com.lzw.springbootmybatismodel.spider;

import com.alibaba.fastjson.JSON;
import com.lzw.springbootmybatismodel.domain.Goods;
import com.lzw.springbootmybatismodel.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
@Service//注释为service，目的是使用bean中对象
public class GoodsSpiderPipeline implements Pipeline {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取Process传递来的数据
        List<Goods> goodsList = resultItems.get("goods");
        System.out.println("Pipeline中的List:"+ JSON.toJSONString(goodsList));
        //向数据库中添加数据
        goodsMapper.insertGoodsList(goodsList);
    }
}
