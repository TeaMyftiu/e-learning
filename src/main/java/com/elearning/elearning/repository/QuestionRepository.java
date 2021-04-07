package com.elearning.elearning.repository;

import com.elearning.elearning.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    static final String GET_QUESTION_BY_USER_ID = "SELECT * FROM question WHERE user_id = :userId ";

    @Query(value = GET_QUESTION_BY_USER_ID, nativeQuery = true)
    List<Question> findQuestionByUserId(Long userId);

    static final String UPDATE_QUESTION = " UPDATE question SET title = :title , description = :description , user_id = :userId WHERE id = :id ";

    @Query(value = UPDATE_QUESTION, nativeQuery = true)
    @Modifying
    @Transactional
    void updateQuestion(Long id, String title, String description, Long userId);
}
