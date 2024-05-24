package com.exercise.blog.service;

import com.exercise.blog.Dto.BlogPostDto;
import com.exercise.blog.Exception.BlogPostNotFoundException;
import com.exercise.blog.Exception.UserNotFoundException;
import com.exercise.blog.model.BlogPost;
import com.exercise.blog.model.User;
import com.exercise.blog.repository.BlogPostRepository;
import com.exercise.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private UserRepository userRepository;

    public String postBlogPost(BlogPostDto blogPostDto) {
        Optional<User> userOpt = userRepository.findById(blogPostDto.getUserId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            BlogPost blogPost = new BlogPost(
                    blogPostDto.getCategory(),
                    blogPostDto.getTitle(),
                    blogPostDto.getCover(),
                    blogPostDto.getContent(),
                    blogPostDto.getReadingTime(),
                    user
            );
            blogPostRepository.save(blogPost);
            return "Blog post with id " + blogPost.getBlogPostId() + " saved.";
        } else {
            throw new UserNotFoundException("User with id " + blogPostDto.getUserId() + " not found.");
        }
    }


    public Page<BlogPost> getBlogPosts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogPostRepository.findAll(pageable);
    }

    public BlogPost getBlogPostById(int blogPostId) {
        Optional<BlogPost> blogPostOpt = blogPostRepository.findById(blogPostId);
        if(blogPostOpt.isPresent()){
            return blogPostOpt.get();
        } else {
            throw new BlogPostNotFoundException("Blog post id: " + blogPostId + " not found.");
        }
    }

    public BlogPost putBlogPost(int blogPostId, BlogPostDto blogPostUpd) {
        BlogPost blogPost = getBlogPostById(blogPostId);
        if (blogPost != null) {

            blogPost.setCategory(blogPostUpd.getCategory());
            blogPost.setTitle(blogPostUpd.getTitle());
            blogPost.setCover(blogPostUpd.getCover());
            blogPost.setContent(blogPostUpd.getContent());
            blogPost.setReadingTime(blogPostUpd.getReadingTime());

            Optional<User> userOpt = userRepository.findById(blogPostUpd.getUserId());

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                blogPost.setUser(user);
                userRepository.save(user);
                return blogPost;
            } else {
                throw new UserNotFoundException("User with id " + blogPostUpd.getUserId() + " not found.");
            }
        } else {
            throw new BlogPostNotFoundException("Post with id " + blogPostId + " not found.");
        }
    }

    public String  deleteBlogPost(int blogPostId) {
        BlogPost blogPost = getBlogPostById(blogPostId);
        if (blogPost != null) {
            blogPostRepository.delete(blogPost);
            return "Post with id " + blogPostId + " deleted successfully.";
        } else {
            throw new BlogPostNotFoundException("Post with id " + blogPostId + " not found.");
        }
    }
}
