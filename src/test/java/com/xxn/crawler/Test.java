package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.GetAllByUrl;
import com.xxn.crawler.crawlerUtiles.GetImage;
import com.xxn.crawler.crawlerUtiles.GetNews;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

public class Test {
    public static void main(String[] args) {
//        GetAllByUrl getAllByUrl = new GetAllByUrl("https://www.jd.com/", "D:\\test");
//        getAllByUrl.start();
        GetNews getNews = new GetNews("https://www.hhu.edu.cn/2024/0629/c166a285661/page.htm","D:\\test");
        getNews.start();

    }
}
