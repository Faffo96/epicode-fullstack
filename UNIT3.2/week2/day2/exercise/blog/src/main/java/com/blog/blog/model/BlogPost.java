package com.blog.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue
    private int blogPostId;
    private static int count;
    private String category;
    private String title;
    private String cover;
    private String content;
    @JoinColumn(name = "reading_time")
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BlogPost() {};

    public BlogPost(String category, String title, String cover, String content, int readingTime, User user) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.user = user;
        count++;
        this.blogPostId = count;
    }
}
