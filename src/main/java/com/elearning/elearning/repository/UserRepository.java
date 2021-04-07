package com.elearning.elearning.repository;

import com.elearning.elearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    static final String UPDATE_USER = " UPDATE user SET name = :name , surname = :surname , enabled = 1 WHERE id= :id ";

    @Modifying
    @Transactional
    @Query(value = UPDATE_USER, nativeQuery = true)
    void updateUserById(Long id, String name, String surname);

    static final String DISABLE_USER = " UPDATE user SET name = :name , surname = :surname , enabled = :enabled WHERE id = :id ";

    @Modifying
    @Transactional
    @Query(value = DISABLE_USER, nativeQuery = true)
    void enableOrDisableUserById(Long id, String name, String surname, Integer enabled);
}
