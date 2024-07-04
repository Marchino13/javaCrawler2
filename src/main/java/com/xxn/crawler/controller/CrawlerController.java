package com.xxn.crawler.controller;

import com.xxn.crawler.crawlerUtiles.MyTask;
import com.xxn.crawler.result.Result;
import com.xxn.crawler.crawlerUtiles.GetAllByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    private String taskStatus = "Pending"; // 任务状态：Pending, Success, Failed
    @PostMapping("/getAllByUrl")
    public String getByUrl(@RequestBody String url) {
//        taskStatus = "Pending"; // 重置任务状态

        spider = new GetAllByUrl(url, "D:\\test");
        String stringJson = spider.start();
        try {
            Thread.sleep(2000); // 暂停当前线程1秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        taskStatus = "Success";

        System.out.println(url);
        return "Success";
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
