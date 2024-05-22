package com.blog.blog.Dto;

import lombok.Data;

@Data
public class BlogPostDto {
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    private int userId;
}
