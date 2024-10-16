package com.taskflow.repository;

import com.taskflow.model.Task;
import com.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);

    Optional<List<Task>> getTasksByCreatorId(Long id);
}
