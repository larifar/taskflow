package com.taskflow.service;

import com.taskflow.model.Requirement;
import com.taskflow.repository.RequirementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RequirementService {
    @Autowired
    private RequirementRepo repository;

    @Autowired
    private TaskService taskService;

    public Requirement save(Requirement requirement){
        return repository.save(requirement);
    }

    public Optional<Requirement> findById(Long id) {
        return repository.findById(id);
    }

    public List<Requirement> getAllByTask(Long taskId){
        if (!taskService.existsById(taskId)) {
            return Collections.emptyList();
        }
        return repository.getRequirementsByTaskId(taskId).orElse(Collections.emptyList());

    }

    public Boolean deleteById(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
