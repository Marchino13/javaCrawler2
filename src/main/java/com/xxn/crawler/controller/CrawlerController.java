package com.xxn.crawler.controller;

import com.xxn.crawler.crawlerUtiles.MyTask;
import com.xxn.crawler.result.Result;
import com.xxn.crawler.crawlerUtiles.GetAllByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
   @GetMapping("/getAllByUrl")
   public Result<String> getByUrl(String url, String path) {
        spider = new GetAllByUrl(url, path);
        String stringJson = spider.start();
        return Result.success("爬取成功");
    }

    /***
     * @description 停止爬取
     * @param:
     * @return com.xxn.crawler.result.Result<java.lang.String>
     * @author Marchino
     * @date 22:00 2024/7/3
     */
    public Result<String> stop() {
        spider.stop();
        return Result.success("爬取结束");
    }

    @GetMapping("/startScheduledCrawler")
    public void ScheduledCrawler() {
        spider = new GetAllByUrl("https://www.baidu.com/", "D:\\test");
        MyTask myTask = new MyTask(0L, 5L, spider);
        myTask.startTask();
    }

}
