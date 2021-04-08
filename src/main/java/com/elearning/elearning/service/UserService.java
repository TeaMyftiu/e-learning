package com.elearning.elearning.service;

import com.elearning.elearning.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User retrieveUserById(Long id);

    List<User> getAllUser();

    void enableOrDisableUser(Long id, String name, String surname, Integer enabled);

    void updateUserById(Long id, String name, String surname);
}
