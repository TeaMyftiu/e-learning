package com.elearning.elearning.service;

import com.elearning.elearning.model.Blog;
import com.elearning.elearning.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog addBlog(Blog blog) {
        System.out.println("Service USER   l" + blog.getUser().getId());
        blogRepository.save(blog);
        return blog;
    }

    public Blog retrieveBlog(Long id) {
        return blogRepository.getOne(id);
    }

    public List<Blog> retrieveBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsOfOneUser(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    public String deleteBlog(Long id) {
        blogRepository.deleteById(id);
        return "The blog is deleted";
    }

    public void updateBlog(Long id, String title, String description, Long userId) {
        blogRepository.updateBlog(id, title, description, userId);
    }

}
