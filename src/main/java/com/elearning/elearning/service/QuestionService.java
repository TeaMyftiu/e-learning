package com.elearning.elearning.service;

import com.elearning.elearning.model.Question;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    List<Question> getAll();

    List<Question> getQuestionByUserId(Long userId);

    Question getQuestionById(Long id);

    void deleteQuestion(Long id);

    void updateQuestion(Long id, String title, String description, Long userId);
}
