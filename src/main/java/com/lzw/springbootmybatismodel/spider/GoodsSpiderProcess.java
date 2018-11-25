package com.lzw.springbootmybatismodel.spider;

import com.lzw.springbootmybatismodel.domain.Goods;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于抓取网页信息的类
 */
@Service
public class GoodsSpiderProcess implements PageProcessor {

    //设置站点信息，可以设置重复请求的次数、超时时间等信息
    private Site site = Site.me().setRetryTimes(3).setTimeOut(5000);


    @Override
    public void process(Page page) {
        //抓取指定规则的数据，并将数据存储，规则自己指定，随页面变更
        List<Goods> goodsList = new LinkedList<>();
        //商品标题
        List<String> goodstitles = page.getHtml().xpath("a[@class='mainTitle']/text()").all();
        //商品价格
        List<String> goodsprices = page.getHtml().xpath("p[@class='proPrice']/em[@class='num']/@yhdprice").all();
        //商品图片地址
        List<String> goodsphotos = page.getHtml().xpath("a[@class='img']/img/@src").all();
        for (int i=0;i<goodstitles.size();i++){
            System.out.println("标题："+goodstitles.get(i)+"价格："+goodsprices.get(i)+"图片地址："+goodsphotos.get(i));
            Goods goods = new Goods();
            goods.setGoodsName(goodstitles.get(i));
            goods.setGoodsImg(goodsphotos.get(i));
            goods.setGoodsPrice(Double.valueOf(goodsprices.get(i)));
            goodsList.add(goods);
        }
        System.out.println("Process中的List："+goodsList);
        //将获取到的数据传递到指定处理器
        page.putField("goods",goodsList);

        /**
         * 如果网页分页且有url有规律，可执行以下代码，这里以博客园的代码为例
         * //只有主页才需要生成分页数据
         *         if(page.getUrl().get().equals("https://www.cnblogs.com/")) {
         *             //分页抓取
         *
         *             //获取总共多少页
         *             List<String> targetTotal = page.getHtml().xpath("div[@id='paging_block']/div/a/text()").all();
         *             int total = Integer.parseInt(targetTotal.get(targetTotal.size() - 2));
         *             //获取分页的url路径
         *             List<String> targetLinks = page.getHtml().xpath("div[@id='paging_block']/div/a/@href").all();
         *             String pageurl = targetLinks.get(targetLinks.size() - 2);
         *             //循环生成所有分页的url
         *             List<String> nextUrl = new ArrayList<String>();
         *             for (int i = 2; i <= total; i++) {
         *                 nextUrl.add("https://www.cnblogs.com/sitehome/p/" + i);
         *             }
         *             //继续抓取
         *             page.addTargetRequests(nextUrl);
         *         }
         *        // System.err.println(nextUrl);
         */
    }

    @Override
    public Site getSite() {
        return site;
    }

}
