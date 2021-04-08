package com.elearning.elearning.service;

import com.elearning.elearning.model.Lab;

import java.util.List;

public interface LabService {

    Lab saveLab(Lab lab);

    List<Lab> getAll();

    Lab getLabById(Long id);

    List<Lab> getLabByUserId(Long userId);

    void deleteLabById(Long id);

    void updateLab(Long id, String title, String description, Long userId);
}
