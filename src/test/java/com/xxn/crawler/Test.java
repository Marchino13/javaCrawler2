package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.GetAllByUrl;
import com.xxn.crawler.crawlerUtiles.GetImage;
import com.xxn.crawler.crawlerUtiles.GetNews;
import com.xxn.crawler.pojo.News;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

public class Test {
    public static void main(String[] args) {
//        GetImage getImage = new GetImage("https://www.jd.com/", "D:\\test");
//        getImage.start();
        GetNews getNews = new GetNews("https://www.hhu.edu.cn/2024/0703/c166a285772/page.htm","D:\\test");
        News start = getNews.start();
        System.out.println(start.getContent() + " 111111");


    }
}
