package com.xxn.crawler.crawlerUtiles;

import com.xxn.crawler.pojo.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class GetNews implements PageProcessor{

    private String url;
    private String path;
    private Spider spider;

    public String title;
    public String source;
    public String publishTime;
    public String articleText;

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
        title = html.css("#d-container > div > div.infobox > div > h1", "text").get();
        title = title + "</br>";
        page.putField("标题", title);
        source = html.css("#d-container > div > div.infobox > div > p > span:nth-child(1)", "text").get();
        source = source + "</br>";
        page.putField("来源", source);
        publishTime = html.css("#d-container > div > div.infobox > div > p > span:nth-child(2)", "text").get();
        publishTime = publishTime + "</br>";
        page.putField("发布时间", publishTime);
        List<String> text1 = html.css("#d-container > div > div.infobox > div > div > div > div > p > span","text").all();
        List<String> text2 = html.css("#d-container > div > div.infobox > div > div > div > div > p","text").all();
        articleText = text1.toString() + text2.toString();
        page.putField("文章内容",articleText);


    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public News start() {
        spider = Spider.create(new GetNews())
                //设置url
                .addUrl(url)
                .addPipeline(new FilePipeline(path))
                //设置布隆过滤器
                .thread(5);
        spider.start();

        News news = new News();
        news.setTitle(title);
        news.setTime(source + publishTime);
        news.setContent(articleText);
        return news;
    }
}
