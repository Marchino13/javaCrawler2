package com.xxn.crawler;

import com.xxn.crawler.crawlerUtiles.MyTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marchino
 * @date 2024/7/3 22:32
 * @description
 */

@Component
public class dingshiTest {
    @Scheduled(cron = "0/3 * * * * ?")
    public static void main(String[] args) {
//        MyTask myTask = new MyTask(0, 5);
//        myTask.startTask(spider);
    }
}
