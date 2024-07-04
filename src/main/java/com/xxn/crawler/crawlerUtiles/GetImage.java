package com.xxn.crawler.crawlerUtiles;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marchino
 * @date 2024/7/3 22:07
 * @description
 */

public class GetImage implements PageProcessor {

    private String url;
    private String path;
    private Spider spider;
    private String imageSiteUrl;

    private ArrayList<String> imagesUrl;
    public GetImage() {
    }

    public GetImage(String url, String path) {
        this.url = url;
        this.path = path;
    }

    public GetImage(String url, String path, String imageSiteUrl) {
        this.url = url;
        this.path = path;
        this.imageSiteUrl = imageSiteUrl;
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> all = html.$("img","src").regex(".*https.*").all();
        page.putField("url", all);

        imagesUrl = (ArrayList<String>) all;

//        downLoadImg(imageSiteUrl);
    }

//    public void downLoadImg(String imageSiteUrl){
//        try {
//            Document document = Jsoup.connect(imageSiteUrl).get();
//            Elements elements = document.select("li.new-search-works-item");
//            for (int i = 0; i < elements.size(); i++) {
//                Elements select = elements.get(i).select("a > img");
//                Connection.Response response = Jsoup.connect("https:" + select.attr("src")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0").ignoreContentType(true).execute();
//                //添加ip代理版本Jsoup.connect(select.attr("src")).proxy().execute();
//                //模拟浏览器Jsoup.connect(select.attr("src")).userAgent().execute();
//                String name = select.attr("alt");
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.bodyAsBytes());
//
//                FileUtils.copyInputStreamToFile(byteArrayInputStream,new File("D://fileItem//"+ name + ".png"));
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public ArrayList<String> start() {
        spider = Spider.create(this)
                .addUrl(url)
                .thread(5);

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
        return imagesUrl;
    }
}
