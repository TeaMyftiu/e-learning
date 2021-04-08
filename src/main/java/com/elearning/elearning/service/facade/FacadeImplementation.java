package com.elearning.elearning.service.facade;

import com.elearning.elearning.model.Blog;
import com.elearning.elearning.model.Lab;
import com.elearning.elearning.model.Question;
import com.elearning.elearning.model.User;
import com.elearning.elearning.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeImplementation implements Facade {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private LabService labService;
    @Autowired
    private QuestionService questionService;

    @Override
    public Blog addBlog(Blog blog) {
        return blogService.addBlog(blog);
    }

    @Override
    public Blog retrieveBlogById(Long id) {
        return blogService.retrieveBlogById(id);
    }

    public List<Blog> retrieveBlogs() {
        return blogService.retrieveBlogs();
    }

    @Override
    public List<Blog> getBlogsOfOneUser(Long userId) {
        return blogService.getBlogsOfOneUser(userId);
    }

    @Override
    public void deleteBlog(Long id) {
        blogService.deleteBlog(id);
    }

    @Override
    public void updateBlog(Long id, String title, String description, Long userId) {
        blogService.updateBlog(id, title, description, userId);
    }

    @Override
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @Override
    public User retrieveUserById(Long id) {
        return userService.retrieveUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public void enableOrDisableUser(Long id, String name, String surname, Integer enabled) {
        userService.enableOrDisableUser(id, name, surname, enabled);
    }

    @Override
    public void updateUserById(Long id, String name, String surname) {
        userService.updateUserById(id, name, surname);
    }

    @Override
    public Lab saveLab(Lab lab) {
        return labService.saveLab(lab);
    }

    @Override
    public List<Lab> getAllLabs() {
        return labService.getAll();
    }


    @Override
    public Lab getLabById(Long id) {
        return labService.getLabById(id);
    }

    @Override
    public List<Lab> getLabByUserId(Long userId) {
        return labService.getLabByUserId(userId);
    }

    @Override
    public void deleteLabById(Long id) {
        labService.deleteLabById(id);
    }

    @Override
    public void updateLab(Long id, String title, String description, Long userId) {
        labService.updateLab(id, title, description, userId);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionService.saveQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @Override
    public List<Question> getQuestionByUserId(Long userId) {
        return questionService.getQuestionByUserId(userId);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionService.getQuestionById(id);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionService.deleteQuestion(id);
    }

    @Override
    public void updateQuestion(Long id, String title, String description, Long userId) {
        questionService.updateQuestion(id, title, description, userId);
    }
}
