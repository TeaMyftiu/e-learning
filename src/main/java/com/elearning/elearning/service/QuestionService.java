package com.elearning.elearning.service;

import com.elearning.elearning.model.Question;
import com.elearning.elearning.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByUserId(Long userId) {
        return questionRepository.findQuestionByUserId(userId);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.getOne(id);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public void updateQuestion(Long id, String title, String description, Long userId) {
        questionRepository.updateQuestion(id, title, description, userId);
    }

}
