package com.elearning.elearning.service.serviceImplementation;

import com.elearning.elearning.model.Blog;
import com.elearning.elearning.repository.BlogRepository;
import com.elearning.elearning.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog addBlog(Blog blog) {
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog retrieveBlogById(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public List<Blog> retrieveBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getBlogsOfOneUser(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void updateBlog(Long id, String title, String description, Long userId) {
        blogRepository.updateBlog(id, title, description, userId);
    }

}
