package com.elearning.elearning.common;

import com.elearning.elearning.service.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseAction {

    @Autowired
    protected Facade facade;

}
