package com.blog.blog.model;

import lombok.Data;

@Data
public class BlogPost {
    private int blogPostId;
    private static int count;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;

    public BlogPost(String category, String title, String cover, String content, int readingTime) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        count++;
        this.blogPostId = count;
    }
}
