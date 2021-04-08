package com.elearning.elearning.service.facade;

import com.elearning.elearning.model.Blog;
import com.elearning.elearning.model.Lab;
import com.elearning.elearning.model.Question;
import com.elearning.elearning.model.User;

import java.util.List;

public interface Facade {
    Blog addBlog(Blog blog);

    Blog retrieveBlogById(Long id);

    List<Blog> retrieveBlogs();

    List<Blog> getBlogsOfOneUser(Long userId);

    void deleteBlog(Long id);

    void updateBlog(Long id, String title, String description, Long userId);

    User saveUser(User user);

    User retrieveUserById(Long id);

    List<User> getAllUser();

    void enableOrDisableUser(Long id, String name, String surname, Integer enabled);

    void updateUserById(Long id, String name, String surname);

    Lab saveLab(Lab lab);

    List<Lab> getAllLabs();

    Lab getLabById(Long id);

    List<Lab> getLabByUserId(Long userId);

    void deleteLabById(Long id);

    void updateLab(Long id, String title, String description, Long userId);

    Question saveQuestion(Question question);

    List<Question> getAllQuestions();

    List<Question> getQuestionByUserId(Long userId);

    Question getQuestionById(Long id);

    void deleteQuestion(Long id);

    void updateQuestion(Long id, String title, String description, Long userId);
}
