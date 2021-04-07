package com.elearning.elearning.validation;

import com.elearning.elearning.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validation {

    public boolean isEmpty(List<User> users) {
        for (User user : users) {
            if (user.getEnabled() == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isPresent(Object inputObject, List<?> objects) {
        for (Object object : objects) {
            if (object.equals(inputObject)) {
                return true;
            }
        }
        return false;
    }
}
