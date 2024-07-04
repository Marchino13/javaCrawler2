package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.GetImage;
import com.xxn.crawler.crawlerUtiles.GetNews;
import com.xxn.crawler.pojo.News;

public class Test {
    public static void main(String[] args) {

        GetImage getImages = new GetImage("https://www.nipic.com/", "D://test//pic","https://www.nipic.com/topic/show_27202_1.html");
        getImages.start();
//        GetNews getNews = new GetNews("https://www.hhu.edu.cn/2024/0629/c166a285661/page.htm","D:\\test");
//        News start = getNews.start();
//        System.out.println(start.getTitle() + " " + start.getTime() + " " + start.getContent());

    }
}
