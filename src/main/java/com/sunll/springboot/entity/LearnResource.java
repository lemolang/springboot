package com.sunll.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LearnResource implements Serializable{

    @JSONField(serialize=false)
    private Long id;

    @NotNull(message = "作者不能为空")
    private String author;

    @NotNull(message = "标题不能为空")
    private String title;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}