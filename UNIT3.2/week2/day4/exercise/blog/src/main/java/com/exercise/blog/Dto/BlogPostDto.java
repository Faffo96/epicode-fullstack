package com.exercise.blog.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogPostDto {
    @NotBlank
    @Size(min = 3, max = 15)
    private String category;
    @NotBlank
    @Size(min = 3, max = 50)
    private String title;
    @NotBlank
    @Size(min = 3, max = 100)
    private String cover;
    @NotBlank
    @Size(min = 5, max = 200)
    private String content;
    @NotNull
    @Min(3)
    private int readingTime;
    @NotNull
    @Min(0)
    private int userId;
}
