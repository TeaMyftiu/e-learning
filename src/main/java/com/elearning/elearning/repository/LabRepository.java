package com.elearning.elearning.repository;

import com.elearning.elearning.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {

    static final String GET_LAB_BY_USER_ID = " SELECT * FROM lab WHERE user_id = :userId ";

    @Query(value = GET_LAB_BY_USER_ID, nativeQuery = true)
    List<Lab> findLabByUserId(Long userId);

    static final String UPDATE_LAB = " UPDATE lab SET title = :title , description = :description , user_id = :userId WHERE id = :id ";

    @Modifying
    @Transactional
    @Query(value = UPDATE_LAB, nativeQuery = true)
    void updateLabById(Long id, String title, String description, Long userId);
}
