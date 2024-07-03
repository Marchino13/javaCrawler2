package com.xxn.crawler.crawlerUtiles;

import us.codecraft.webmagic.Spider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyTask {

    private long initialDelay;
    private long period;
    private GetAllByUrl spider;

    public MyTask(long initialDelay, long period, GetAllByUrl spider) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.spider = spider;
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
        spider.start();

    }
}