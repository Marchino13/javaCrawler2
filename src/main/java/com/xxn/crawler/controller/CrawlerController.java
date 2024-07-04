package com.xxn.crawler.controller;

import com.xxn.crawler.crawlerUtiles.*;
import com.xxn.crawler.pojo.News;
import com.xxn.crawler.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Marchino
 * @date 2024/7/2 21:30
 * @description
 */

@RestController
@RequestMapping("/crawler")
public class CrawlerController {



    /***
     * @description
     * @param: url 要爬取的网站url
     * @param: path 保存路径
     * @return com.xxn.crawler.result.Result<java.lang.String>
     * @author Marchino
     * @date 21:56 2024/7/3
     */
    @PostMapping("/getAllByUrl")
    public String getByUrl(HttpSession session, @RequestBody String url) {

        GetNews getNews = new GetNews(url, null);
        News news = getNews.start();

        session.setAttribute("news", news);
        return "Success";
    }


    /***
     * @description 下载新闻
     * @param: session
 * @param: httpServletResponse
     * @return void
     * @author Marchino
     * @date 17:04 2024/7/4
     */
    @PostMapping("/download")
    public void download(HttpSession session, HttpServletResponse httpServletResponse) {
        News news = (News) session.getAttribute("news");

        PdfUtil.downloadNews(news.getTitle(), news.getTime(), news.getContent(), httpServletResponse);


    }

    /***
     * @description 预览
     * @param: session
     * @return com.xxn.crawler.pojo.News
     * @author Marchino
     * @date 10:13 2024/7/4
     */

    @GetMapping("/preview")
    public News getSessionNews(HttpSession session) {
        Object attribute = session.getAttribute("news");
        News news = (News) attribute;
        System.out.println(news.getTitle());
        return news;
    }

    @PostMapping("/getImage")
    public String getImage(@RequestBody String url, HttpSession session){
        GetImage getImages = new GetImage("https://www.nipic.com/", "D:\\test\\pic");
        ArrayList<BufferedImage> images = getImages.start();
        session.setAttribute("images", images);
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
    @GetMapping("/startScheduledCrawler")
    public void ScheduledCrawler(int interval, String url, String path, boolean flag) {
        if (flag){//定时抓取文章
            GetNews news = new GetNews(url, null);
            new TaskGetNews(0, interval, path, news).startTask();
        }else {//定时抓取图片
            GetImage image = new GetImage(url, null);
            new TaskGetImage(0, interval, path, image).startTask();
        }

    }


}
