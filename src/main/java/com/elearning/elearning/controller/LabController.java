package com.elearning.elearning.controller;

import com.elearning.elearning.common.BaseAction;
import com.elearning.elearning.common.Validation;
import com.elearning.elearning.model.Lab;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/e-learning")
public class LabController extends BaseAction {

    @RequestMapping(value = "/addLab", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addLab(@RequestBody Lab lab) {
        try {
            if (!Validation.isPresent(facade.retrieveUserById(lab.getUser().getId()), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user id you want to sign in does not exist");
            }
            return new ResponseEntity<>(facade.saveLab(lab), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getLabs", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        try {
            if (facade.getAllLabs().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no labs available");
            }
            return new ResponseEntity<>(facade.getAllLabs(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getLab/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLabById(@Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.getLabById(id), facade.getAllLabs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The lab with id: " + id + " does not exist");
            }
            return new ResponseEntity<>(facade.getLabById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/getLabs/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLabByUserId(@Valid @PathVariable("userId") Long userId) {
        try {
            if (!Validation.isPresent(facade.retrieveUserById(userId), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user Id does not exist");
            }
            return new ResponseEntity<>(facade.getLabByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/deleteLab/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLab(@Valid @PathVariable("id") Long id) {
        try {
            if (!Validation.isPresent(facade.getLabById(id), facade.getAllLabs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This lab does not exists");
            }
            facade.deleteLabById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Lab with id: " + id + " is deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @RequestMapping(value = "/updateLab/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity updateLab(@PathVariable("id") Long id, @RequestBody Lab updatedLab) {
        try {
            if (!Validation.isPresent(facade.getLabById(id), facade.getAllLabs())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This lab does not exists");
            }
            if (!Validation.isPresent(facade.retrieveUserById(updatedLab.getUser().getId()), facade.getAllUser())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user Id you trying to sign in does not exist");
            }
            facade.updateLab(id, updatedLab.getTitle(), updatedLab.getDescription(), updatedLab.getUser().getId());
            return ResponseEntity.status(HttpStatus.OK).body("The lab with id:" + id + " is updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
