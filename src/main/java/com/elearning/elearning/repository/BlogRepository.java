package com.elearning.elearning.repository;

import com.elearning.elearning.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {


    static final String GET_BLOGS_BY_USER_ID = " Select * From blog Where blog.user_id = :userId ";

    @Query(value = GET_BLOGS_BY_USER_ID, nativeQuery = true)
    List<Blog> findByUserId(Long userId);

    static final String UPDATE_BLOG = " UPDATE blog SET title = :title , description = :description , user_id = :userId WHERE id= :id ";

    @Modifying
    @Transactional
    @Query(value = UPDATE_BLOG, nativeQuery = true)
    void updateBlog(Long id, String title, String description, Long userId);

}
