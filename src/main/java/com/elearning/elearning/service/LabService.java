package com.elearning.elearning.service;

import com.elearning.elearning.model.Lab;
import com.elearning.elearning.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    public Lab saveLab(Lab lab) {
        return labRepository.save(lab);
    }

    public List<Lab> getAll() {
        return labRepository.findAll();
    }

    public Lab getLabById(Long id) {
        return labRepository.getOne(id);
    }

    public List<Lab> getLabByUserId(Long userId) {
        return labRepository.findLabByUserId(userId);
    }

    public void deleteLabById(Long id) {
        labRepository.deleteById(id);
    }

    public void updateLab(Long id, String title, String description, Long userId) {
        labRepository.updateLabById(id, title, description, userId);
    }
}
