package com.xxn.crawler.crawlerUtiles;

import com.xxn.crawler.pojo.News;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskGetImage {

    private long initialDelay;
    private long period;
    private String savePath;//保存路径
    private GetImage spider;
    private int i = 0;

    public TaskGetImage(long initialDelay, long period, String savePath, GetImage spider) {
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
//        ArrayList<BufferedImage> start = spider.start();
        ArrayList<String> imagesUrl = spider.start();
        PdfUtil.imageToPDF(imagesUrl, savePath + i + ".pdf");
        i++;

    }
}