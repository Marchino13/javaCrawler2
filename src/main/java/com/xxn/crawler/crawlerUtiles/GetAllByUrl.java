package com.xxn.crawler.crawlerUtiles;

import com.xxn.crawler.controller.MyProcessor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Marchino
 * @date 2024/7/3 16:27
 * @description 传入一个url，使用默认配置， 获取该url的页面信息,不对信息进行任何处理
 */

@Component
public class GetAllByUrl implements PageProcessor {
    private String url;
    private String path;
    private Spider spider;
    //TODO 添加一个list，用于存放抓取到的数据

    public GetAllByUrl() {
    }

    public GetAllByUrl(String url, String path) {
        this.url = url;
        this.path = path;
    }

    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

    @Override
    public void process(Page page) {
        //访问黑马的首页
        //解析首页中所有的链接地址
        Html html = page.getHtml();
        //html.css("a","href");
        Selectable links = html.links();
        List<String> allLinks = links.all();
        //把链接地址添加到访问队列中
        page.addTargetRequests(allLinks);
        //把页面传递给pipeline，由pipeline保存到磁盘
        page.putField("html", html.get());
    }

    @Override
    public Site getSite() {
        return site;
    }

    /***
     * @description 爬取数据并以String格式返回, 同时将数据保存到本地
     * @param:
     * @return java.lang.String
     * @author Marchino
     * @date 21:59 2024/7/3
     */
    public String start() {
        spider = Spider.create(new GetAllByUrl())
                .addUrl(url)
                .addPipeline(new FilePipeline(path))
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

        System.out.println("Spider execution completed.");
        return null;
    }

    public void stop() {
        spider.stop();
    }


}
