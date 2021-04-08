package com.elearning.elearning.service.serviceImplementation;

import com.elearning.elearning.model.Lab;
import com.elearning.elearning.repository.LabRepository;
import com.elearning.elearning.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImplementation implements LabService {

    @Autowired
    private LabRepository labRepository;

    @Override
    public Lab saveLab(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public List<Lab> getAll() {
        return labRepository.findAll();
    }

    @Override
    public Lab getLabById(Long id) {
        return labRepository.getOne(id);
    }

    @Override
    public List<Lab> getLabByUserId(Long userId) {
        return labRepository.findLabByUserId(userId);
    }

    @Override
    public void deleteLabById(Long id) {
        labRepository.deleteById(id);
    }

    @Override
    public void updateLab(Long id, String title, String description, Long userId) {
        labRepository.updateLabById(id, title, description, userId);
    }
}
