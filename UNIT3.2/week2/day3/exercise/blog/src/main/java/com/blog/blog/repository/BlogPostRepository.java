package com.blog.blog.repository;

import com.blog.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
