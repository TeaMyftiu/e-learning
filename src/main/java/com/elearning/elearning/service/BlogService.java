package com.elearning.elearning.service;

import com.elearning.elearning.model.Blog;

import java.util.List;

public interface BlogService {

    Blog addBlog(Blog blog);

    Blog retrieveBlogById(Long id);

    List<Blog> retrieveBlogs();

    List<Blog> getBlogsOfOneUser(Long userId);

    void deleteBlog(Long id);

    void updateBlog(Long id, String title, String description, Long userId);
}
