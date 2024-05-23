package com.exercise.blog.controller;

import com.exercise.blog.Dto.BlogPostDto;
import com.exercise.blog.Exception.BadRequestException;
import com.exercise.blog.model.BlogPost;
import com.exercise.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String postBlogPost(@RequestBody @Validated BlogPostDto blogPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }

        return blogPostService.postBlogPost(blogPost);
    }

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "title") String sortBy) {
        return blogPostService.getBlogPosts(page, size, sortBy);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost getBlogPostById(@PathVariable int blogPostId) {
        return blogPostService.getBlogPostById(blogPostId);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody BlogPost putBlogPost(@PathVariable int postId, @RequestBody @Validated BlogPostDto blogPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }
        return blogPostService.putBlogPost(postId, blogPost);
    }

    @DeleteMapping("/{postId}")
    public String deleteBlogPost(@PathVariable int postId) {
        return blogPostService.deleteBlogPost(postId);
    }
}
