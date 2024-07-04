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
        ArrayList<String> strings = new ArrayList<>();
        strings.add("https://pic.ntimg.cn/BannerPic/20240701/original/20240701140621_1.jpg");
        strings.add("https://pic.ntimg.cn/BannerPic/20240701/original/20240701140734_1.jpg");

        PdfUtil.imageToPDF(strings,"D://test//webmagic//test.pdf");

    }
}
