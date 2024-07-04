package com.xxn.crawler.crawlerUtiles;

import com.xxn.crawler.pojo.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
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

@Component
public class GetNews implements PageProcessor{

    private String url;
    private String path;
    private Spider spider;
    private News news;

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

        page.putField("标题", title);
        String source = html.css("#d-container > div > div.infobox > div > p > span:nth-child(1)", "text").get();

        page.putField("来源", source);
        String publishTime = html.css("#d-container > div > div.infobox > div > p > span:nth-child(2)", "text").get();

        page.putField("发布时间", publishTime);
//        List<String> text = html.xpath("//div[@class='wp_articlecontent']").all();
//        page.putField("文章内容",text);

        List<String> text1 = html.css("#d-container > div > div.infobox > div > div > div > div > p > span","text").all();
        List<String> text2 = html.css("#d-container > div > div.infobox > div > div > div > div > p","text").all();
//        String articleText = text1.toString() + text2.toString();
        String articleText = "";
        for (String s : text1) {
            articleText +=s;
        }
        for (String s : text2) {
            articleText +=s;
            articleText +="</br>";
        }

//        page.putField("articleText", articleText);
        // 创建一个新的 News 对象，并设置数据
        news = new News();
        news.setTitle(title);
        news.setContent(articleText);
        news.setTime(publishTime);
        news.setSource(source);


    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public News start() {
        spider = Spider.create(this)
                //设置url
                .addUrl(url)
                .thread(1);

        // 创建一个线程来运行spider
        Thread spiderThread = new Thread(spider);

        // 启动spider线程
        spiderThread.start();

        // 等待spider线程执行完毕
        try {
            spiderThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 重新设置中断状态
            e.printStackTrace();
        }
        return news;
    }


}
