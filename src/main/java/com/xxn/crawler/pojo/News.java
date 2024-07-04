package com.xxn.crawler.pojo;

/**
 *
 * @author Marchino
 * @date 2024/7/4 8:44
 * @description
 */

import lombok.Data;

import java.io.Serializable;

/***
 * @description 新闻类
 * @param: null
 * @return
 * @author Marchino
 * @date 8:44 2024/7/4
 */

//@Data
public class News implements Serializable {
    private String title;
    private String content;
    private String time;
    private String source;


//    public News(String title, String content, String time) {
//        this.title = title;
//        this.content = content;
//        this.time = time;
//    }

    public News(String title, String content, String time, String source) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public News() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
