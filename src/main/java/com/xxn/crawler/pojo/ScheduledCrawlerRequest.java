package com.xxn.crawler.pojo;

public class ScheduledCrawlerRequest {
    private String interval;
    private String url;
    private String savePath;
    private boolean flag;

    public ScheduledCrawlerRequest(String interval, String url, String savePath, boolean flag) {
        this.interval = interval;
        this.url = url;
        this.savePath = savePath;
        this.flag = flag;
    }

    public ScheduledCrawlerRequest() {
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}