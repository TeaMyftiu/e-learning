package com.elearning.elearning.controller;

import com.elearning.elearning.common.BaseAction;
import com.elearning.elearning.model.User;
import com.elearning.elearning.service.serviceImplementation.UserServiceImplementation;
import com.elearning.elearning.common.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/e-learning")
public class UserController extends BaseAction {

    @Autowired
    private UserServiceImplementation userServiceImplementation;
    @Autowired
    private Validation validation;

    @RequestMapping(value = "/add_user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        try {
            user.setEnabled(1);
            return new ResponseEntity<>(facade.saveUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }
    }

    @RequestMapping(value = "/getUsers", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        try {
            List<User> users = facade.getAllUser();
            if (Validation.isEmpty(users)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no users active for the moment");
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUserById(@Valid @PathVariable("id") Long id) {
        try {
            User user = facade.retrieveUserById(id);
            List<User> users = facade.getAllUser();
            if (Validation.isPresent(user, users)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }
    }

    @RequestMapping(value = "/disableUser/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> disableUser(@Valid @PathVariable("id") Long id) {
        try {
            User user = facade.retrieveUserById(id);
            if (!Validation.isPresent(user, facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user does not exist");
            }
            if (user.getEnabled() == 0) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user is already disabled");
            }
            facade.enableOrDisableUser(id, user.getName(), user.getSurname(), 0);
            return ResponseEntity.status(HttpStatus.OK).body("The user with id: " + id + " is disabled");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }

    }

    @RequestMapping(value = "/enableUser/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> enableUser(@PathVariable("id") Long id) {
        try {
            User user = facade.retrieveUserById(id);
            if (!Validation.isPresent(user, facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user does not exist");
            }
            if (user.getEnabled() == 1) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user is already enabled");
            }
            facade.enableOrDisableUser(id, user.getName(), user.getSurname(), 1);
            return ResponseEntity.status(HttpStatus.OK).body("The user with id: " + id + " is enabled");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @Valid @PathVariable("id") Long id) {
        try {
            User user = facade.retrieveUserById(id);
            if (!Validation.isPresent(user, facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user does not exist");
            }
            if (user.getEnabled() == 1) {
                facade.updateUserById(id, updatedUser.getName(), updatedUser.getSurname());
                return ResponseEntity.status(HttpStatus.OK).body("The user with id: " + id + " is updated");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user is already disable so you can not update it");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("This is an Internal Server Error");
        }

    }

}
