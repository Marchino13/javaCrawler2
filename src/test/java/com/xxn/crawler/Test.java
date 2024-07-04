package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.GetNews;
import com.xxn.crawler.pojo.News;

public class Test {
    public static void main(String[] args) {
//        GetAllByUrl getAllByUrl = new GetAllByUrl("https://www.jd.com/", "D:\\test");
//        getAllByUrl.start();
        GetNews getNews = new GetNews("https://www.hhu.edu.cn/2024/0629/c166a285661/page.htm","D:\\test");
        News start = getNews.start();
        System.out.println(start.getTitle() + " " + start.getTime() + " " + start.getContent());

    }
}
