package com.xxn.crawler.controller;

import com.xxn.crawler.crawlerUtiles.GetNews;
import com.xxn.crawler.crawlerUtiles.MyTask;
import com.xxn.crawler.pojo.News;
import com.xxn.crawler.result.Result;
import com.xxn.crawler.crawlerUtiles.GetAllByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Marchino
 * @date 2024/7/2 21:30
 * @description
 */

@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    private GetAllByUrl spider;

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
        spider = new GetAllByUrl(url, "D:\\test");
        GetNews getNews = new GetNews(url, "D:\\test");
        News news = getNews.start();

        session.setAttribute("news", news);
        System.out.println(url);
        return "Success";
    }


    @GetMapping("/startScheduledCrawler")
    public void ScheduledCrawler(int initialDelay, int period, String url, String path) {
        spider = new GetAllByUrl(url, path);
        MyTask myTask = new MyTask(initialDelay, period, null, spider);
        myTask.startTask();
    }

    //TODO 下载
    @PostMapping("/download")
    public void download() {
        spider = new GetAllByUrl("https://www.baidu.com/", "D:\\test");
        spider.start();
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
        Object attribute = session.getAttribute("test");
        News news = (News) attribute;
        System.out.println(news.getTitle());
        return (News) session.getAttribute("test");
    }

}
