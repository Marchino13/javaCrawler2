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
        title = title + "</br>";
        page.putField("标题", title);
        String source = html.css("#d-container > div > div.infobox > div > p > span:nth-child(1)", "text").get();
        source = source + "</br>";
        page.putField("来源", source);
        String publishTime = html.css("#d-container > div > div.infobox > div > p > span:nth-child(2)", "text").get();
        publishTime = publishTime + "</br>";
        page.putField("发布时间", publishTime);
        List<String> text = html.xpath("//div[@class='wp_articlecontent']").all();
        page.putField("文章内容",text);


//        String textString = text.toString();
//        News news = new News();
//        news.setTitle(title);
//        news.setContent(textString);
//        news.setTime(publishTime);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public String start() {
        spider = Spider.create(new GetNews())
                //设置url
                .addUrl(url)
                //设置持久层
                .addPipeline(new FilePipeline(path))
                //设置布隆过滤器
                .thread(5);
        spider.start();
        return null;
    }
}
