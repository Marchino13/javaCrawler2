package com.xxn.crawler.controller;

import com.xxn.crawler.crawlerUtiles.*;
import com.xxn.crawler.pojo.News;
import com.xxn.crawler.pojo.ScheduledCrawlerRequest;
import com.xxn.crawler.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Marchino
 * @date 2024/7/2 21:30
 * @description
 */

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    // 输入抓取新闻URL
    @PostMapping("/getWord")
    public String getByUrl(HttpSession session, @RequestBody String url) {
        GetNews getNews = new GetNews(url, null);
        News news = getNews.start();

        session.setAttribute("news", news);
        return "Success";
    }

    // 新闻预览
    @GetMapping("/previewWord")
    public News getSessionNews(HttpSession session) {
        Object attribute = session.getAttribute("news");
        News news = (News) attribute;
        System.out.println(news.getTitle());
        return news;
    }

    // 下载新闻
    @PostMapping("/downloadWord")
    public void download(HttpSession session, HttpServletResponse httpServletResponse) {
        News news = (News) session.getAttribute("news");

        PdfUtil.downloadNews(news.getTitle(), news.getTime(), news.getContent(), httpServletResponse);
    }

    // 输入抓取图片url
    @PostMapping("/getImage")
    public String getImage(HttpSession session, @RequestBody String url) {
        GetImage getImages = new GetImage(url, null);
        ArrayList<String> images = getImages.start();
        session.setAttribute("imagesUrl",images);
        return "Success";
    }

    @PostMapping("/downloadImages")
    public String downloadImage(@RequestBody String path,HttpSession session, HttpServletResponse httpServletResponse) {
        Object attribute = session.getAttribute("imagesUrl");

        ArrayList<String> images = (ArrayList<String>) attribute;
//        PdfUtil.downloadsImages(images, httpServletResponse);
        PdfUtil.imageToPDF(images, path);
        System.out.println("下载成功");
        return "Success";
    }

    /***
     * @description
     * @param: initialDelay
     * @param: period 每多少秒执行一次
     * @param: url 抓取的url
     * @param: path 保存地址
     * @param: flag 抓取图片还是文章
     * @return void
     * @author Marchino
     * @date 11:52 2024/7/4
     */
    @PostMapping("/startScheduledCrawler")
    public void ScheduledCrawler(@RequestBody ScheduledCrawlerRequest reques) {
        String interval = reques.getInterval() ;
        int i = Integer.parseInt(interval);
        String url = reques.getUrl();
        String path = reques.getSavePath();
        boolean flag = reques.isFlag();
        if (flag) {//定时抓取文章
            GetNews news = new GetNews(url, null);
            new TaskGetNews(0, i, path, news).startTask();
        } else {//定时抓取图片
            GetImage image = new GetImage(url, null);
            new TaskGetImage(0, i, path, image).startTask();
        }


    }


}
