package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.GetNews;
import com.xxn.crawler.crawlerUtiles.PdfUtil;
import com.xxn.crawler.crawlerUtiles.TaskGetNews;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 *
 * @author Marchino
 * @date 2024/7/3 22:32
 * @description
 */

@Component
public class dingshiTest {
    //    @Scheduled(cron = "0/3 * * * * ?")
    public static void main(String[] args) {
        GetNews getNews = new GetNews("https://www.hhu.edu.cn/2024/0704/c166a285796/page.htm", null);
        TaskGetNews taskGetNews = new TaskGetNews(0, 3, "D:\\webmagic\\testt", getNews);
        taskGetNews.startTask();
    }
}
