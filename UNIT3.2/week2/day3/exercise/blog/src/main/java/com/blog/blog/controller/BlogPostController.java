package com.blog.blog.controller;

import com.blog.blog.Dto.BlogPostDto;
import com.blog.blog.Exception.BlogPostNotFoundException;
import com.blog.blog.model.BlogPost;
import com.blog.blog.model.User;
import com.blog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/api/blogposts")
    @ResponseStatus(HttpStatus.CREATED)
    public String postBlogPost(@RequestBody BlogPostDto blogPost) {
            return blogPostService.postBlogPost(blogPost);
    }

    @GetMapping("/api/blogposts")
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "title") String sortBy) {
        return blogPostService.getBlogPosts(page, size, sortBy);
    }

    @GetMapping("/api/blogposts/{blogPostId}")
    public BlogPost getBlogPostById(@PathVariable int blogPostId) {
        return blogPostService.getBlogPostById(blogPostId);

        /*if(blogPostOpt.isPresent()){
            return blogPostOpt.get();
        } else {
            throw new BlogPostNotFoundException("Blog post id: " + blogPostId + " not found.");
        }*/
    }

    @PutMapping(path = "/api/blogposts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody BlogPost putBlogPost(@PathVariable int postId, @RequestBody BlogPostDto blogPost) throws Exception {
        return blogPostService.putBlogPost(postId, blogPost);
    }

    @DeleteMapping("/api/blogposts/{postId}")
    public String deleteBlogPost(@PathVariable int postId) throws Exception {
        return blogPostService.deleteBlogPost(postId);
    }
}

