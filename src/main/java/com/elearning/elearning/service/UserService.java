package com.elearning.elearning.service;

import com.elearning.elearning.model.User;
import com.elearning.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User retrieveUserById(Long id) {
        return userRepository.getOne(id);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void enableOrDisableUser(Long id, String name, String surname, Integer enabled) {
        userRepository.enableOrDisableUserById(id, name, surname, enabled);
    }

    public void updateUserById(Long id, String name, String surname) {
        userRepository.updateUserById(id, name, surname);
    }
}
