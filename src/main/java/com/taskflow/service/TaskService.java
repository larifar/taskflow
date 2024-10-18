package com.taskflow.service;

import com.taskflow.exception.ExceptionTaskFlow;
import com.taskflow.model.Task;
import com.taskflow.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo repository;

    @Autowired
    private UserService userService;

    public Task save(Task task) throws ExceptionTaskFlow {
        if (!userService.existsById(task.getCreator().getId())){
            throw new ExceptionTaskFlow("Usuário não encontrado com id: " +  task.getCreator().getId());
        }
        return repository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public List<Task> getAllByUserId(Long userId) throws ExceptionTaskFlow {
        if (!userService.existsById(userId)){
            throw new ExceptionTaskFlow("Usuário não encontrado com id: " +  userId);
        }
        return repository.getTasksByCreatorId(userId).orElse(Collections.emptyList());

    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Boolean deleteById(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
