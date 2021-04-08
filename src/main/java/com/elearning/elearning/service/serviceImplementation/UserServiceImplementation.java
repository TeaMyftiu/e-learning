package com.elearning.elearning.service.serviceImplementation;

import com.elearning.elearning.model.User;
import com.elearning.elearning.repository.UserRepository;
import com.elearning.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieveUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void enableOrDisableUser(Long id, String name, String surname, Integer enabled) {
        userRepository.enableOrDisableUserById(id, name, surname, enabled);
    }

    @Override
    public void updateUserById(Long id, String name, String surname) {
        userRepository.updateUserById(id, name, surname);
    }
}
