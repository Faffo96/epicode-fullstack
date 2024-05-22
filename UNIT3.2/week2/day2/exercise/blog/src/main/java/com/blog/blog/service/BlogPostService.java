package com.blog.blog.service;

import com.blog.blog.Dto.BlogPostDto;
import com.blog.blog.Exception.BlogPostNotFoundException;
import com.blog.blog.Exception.UserNotFoundException;
import com.blog.blog.model.BlogPost;
import com.blog.blog.model.User;
import com.blog.blog.repository.BlogPostRepository;
import com.blog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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






    public List<BlogPost> getPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getBlogPostById(int blogPostId) {
        return blogPostRepository.findById(blogPostId);
    }

    public BlogPost putBlogPost(int blogPostId, BlogPostDto blogPostUpd) {
        Optional<BlogPost> blogPostOpt = getBlogPostById(blogPostId);
        if (blogPostOpt.isPresent()) {
            BlogPost blogPost = blogPostOpt.get();

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
        Optional<BlogPost> blogPostOpt = getBlogPostById(blogPostId);

        if (blogPostOpt.isPresent()) {
            blogPostRepository.delete(blogPostOpt.get());
            return "Post with id " + blogPostId + " deleted successfully.";
        } else {
            throw new BlogPostNotFoundException("Post with id " + blogPostId + " not found.");
        }
    }
}
