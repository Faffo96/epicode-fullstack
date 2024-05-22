package com.blog.blog.service;

import com.blog.blog.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    private List<BlogPost> blogPosts = new ArrayList<>();

    public Optional<BlogPost> getBlogPostById(int postId) {
        return blogPosts.stream().filter(post -> post.getBlogPostId() == postId).findFirst();
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPosts;
    }

    public String postBlogPost(BlogPost blogPost) {
        blogPosts.add(blogPost);
        return "Blog post created with id: " + blogPost.getBlogPostId();
    }

    public BlogPost putBlogPost(int postId, BlogPost blogPostUpd) throws RuntimeException {
        Optional<BlogPost> blogPostOpt = getBlogPostById(postId);
        if (blogPostOpt.isPresent()) {
            BlogPost blogPost = blogPostOpt.get();
            blogPost.setCategory(blogPostUpd.getCategory());
            blogPost.setTitle(blogPostUpd.getTitle());
            blogPost.setCover(blogPostUpd.getCover());
            blogPost.setContent(blogPostUpd.getContent());
            blogPost.setReadingTime(blogPostUpd.getReadingTime());
            return blogPost;
        } else {
            throw new RuntimeException("Blog post not found.");
        }
    }


    public String deleteBlogPost(int postId) throws RuntimeException {
        Optional<BlogPost> blogPostOpt = getBlogPostById(postId);

        if (blogPostOpt.isPresent()) {
            blogPosts.remove(blogPostOpt.get());
            return "Blog post " + postId + " removed.";
        } else {
            throw new RuntimeException("Blog post not found.");
        }
    }
}
