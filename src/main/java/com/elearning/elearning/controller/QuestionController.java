package com.elearning.elearning.controller;

import com.elearning.elearning.model.Question;
import com.elearning.elearning.service.QuestionService;
import com.elearning.elearning.service.UserService;
import com.elearning.elearning.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/e-learning")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private Validation validation;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {

        try {
            if (!validation.isPresent(userService.retrieveUserById(question.getUser().getId()), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user you want to sign this question does not exist");
            }
            return new ResponseEntity<>(questionService.saveQuestion(question), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal ServerError");
        }
    }

    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAll() {
        try {
            List<Question> questions = questionService.getAll();
            if (questions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no questions available");
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getQuestion/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getQuestionById(@Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(questionService.getQuestionById(id), questionService.getAll())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The question with id: " + id + " doeas not exist");
            }
            return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }

    @RequestMapping(value = "/getQuestions/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showQuestionByUserId(@Valid @PathVariable("userId") Long userId) {
        try {
            if (!validation.isPresent(userService.retrieveUserById(userId), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user doesn not exist");
            }
            return new ResponseEntity<>(questionService.getQuestionByUserId(userId), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuestion(@Valid @PathVariable("id") Long id) {
        try {
            if (!validation.isPresent(questionService.getQuestionById(id), questionService.getAll())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This question does not exist");
            }
            questionService.deleteQuestion(id);
            return ResponseEntity.status(HttpStatus.OK).body("This question is deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/updateQuestion/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateQuestion(@Valid @PathVariable Long id, @RequestBody Question updatedQuestion) {
        try {
            if (!validation.isPresent(questionService.getQuestionById(id), questionService.getAll())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This question does not exist");
            }
            if (!validation.isPresent(userService.retrieveUserById(updatedQuestion.getUser().getId()), userService.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user_id you want to update does not exist");
            }
            questionService.updateQuestion(id, updatedQuestion.getTitle(), updatedQuestion.getDescription(), updatedQuestion.getUser().getId());
            return ResponseEntity.status(HttpStatus.OK).body("Question with id: " + id + " is updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
