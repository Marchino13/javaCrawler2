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

@Data
public class News implements Serializable {
    private String  title;
    private String content;
    private String time;
}
