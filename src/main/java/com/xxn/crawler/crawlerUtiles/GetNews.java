package com.xxn.crawler.crawlerUtiles;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.selector.Html;

public class GetNews implements PageProcessor{

    private String url;
    private String path;
    private Spider spider;

    public GetNews(String url, String path) {
        this.url = url;
        this.path = path;
    }

    public GetNews() {
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //获取新闻页面的title；
        String title = html.css("#d-container > div > div.infobox > div > h1", "text").get();
        page.putField("title", title);
        String source = html.css("#d-container > div > div.infobox > div > p > span:nth-child(1)", "text").get();
        page.putField("subHeading", source);
        String publishTime = html.css("#d-container > div > div.infobox > div > p > span:nth-child(2)", "text").get();
        page.putField("publishTime", publishTime);
        String text = html.css("#d-container > div > div.infobox > div > div > div > div > p:nth-child(1) > span", "text").get();
        page.putField("text", text);

    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public String start() {
        //QueueScheduler scheduler = new QueueScheduler();
        //设置布隆过滤器
        //scheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(1000000));
        spider = Spider.create(new GetNews())
                //设置url
                .addUrl(url)
                //设置持久层
                .addPipeline(new FilePipeline(path))
                //设置布隆过滤器
                //.setScheduler(scheduler)
                .thread(5);
        spider.start();
        return null;
    }
}
