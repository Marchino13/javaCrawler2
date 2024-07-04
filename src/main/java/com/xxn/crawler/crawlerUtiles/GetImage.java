package com.xxn.crawler.crawlerUtiles;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

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

    public GetImage() {
    }

    public GetImage(String url, String path) {
        this.url = url;
        this.path = path;
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String src = html.css("a img", "src").get().toString();
        page.putField("src", src);

    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public String start() {
        spider = Spider.create(new GetImage())
                .addUrl(url)
                .addPipeline(new FilePipeline(path))
                .thread(5);
        spider.start();
        return null;
    }
}
