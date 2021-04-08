package com.elearning.elearning.service.serviceImplementation;

import com.elearning.elearning.model.Question;
import com.elearning.elearning.repository.QuestionRepository;
import com.elearning.elearning.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionByUserId(Long userId) {
        return questionRepository.findQuestionByUserId(userId);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.getOne(id);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void updateQuestion(Long id, String title, String description, Long userId) {
        questionRepository.updateQuestion(id, title, description, userId);
    }

}
