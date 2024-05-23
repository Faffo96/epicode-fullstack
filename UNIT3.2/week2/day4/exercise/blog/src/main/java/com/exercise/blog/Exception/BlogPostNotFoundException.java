package com.exercise.blog.Exception;

public class BlogPostNotFoundException extends RuntimeException {
    public BlogPostNotFoundException(String message) {
        super(message);
    }
}
