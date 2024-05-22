package com.blog.blog.controller;

import com.blog.blog.model.BlogPost;
import com.blog.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String postBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostService.postBlogPost(blogPost);
    }

    @GetMapping("/api/blogposts")
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/api/blogposts/{postId}")
    public BlogPost getBlogPostById(@PathVariable int postId) throws Exception {
        Optional<BlogPost> blogPostOpt = blogPostService.getBlogPostById(postId);

        if(blogPostOpt.isPresent()){
            return blogPostOpt.get();
        } else {
            throw new Exception("Blog post id: " + postId + " not found.");
        }
    }

    @PutMapping(path = "/api/blogposts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody BlogPost putBlogPost(@PathVariable int postId, @RequestBody BlogPost blogPost) throws Exception {
        return blogPostService.putBlogPost(postId, blogPost);
    }

    @DeleteMapping("/api/blogposts/{postId}")
    public String deleteBlogPost(@PathVariable int postId) throws Exception {
        return blogPostService.deleteBlogPost(postId);
    }
}

