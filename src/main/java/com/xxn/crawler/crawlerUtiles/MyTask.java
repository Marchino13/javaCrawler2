package com.xxn.crawler.crawlerUtiles;

import com.xxn.crawler.pojo.News;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyTask {

    private long initialDelay;
    private long period;
    private String savePath;//保存路径
    private GetNews spider;

    public MyTask(long initialDelay, long period, String savePath, GetNews spider) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.spider = spider;
        this.savePath = savePath;
    }

    private ScheduledExecutorService executorService;

    public void startTask() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::process, initialDelay, period, TimeUnit.SECONDS);
    }

    public void stopTask() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private void process() {
        System.out.println("基于接口定时任务");
        News start = spider.start();



        //TODO 下载
//        PDFUtils.newsToPdf(String title, String time, String content, String path)

    }
}