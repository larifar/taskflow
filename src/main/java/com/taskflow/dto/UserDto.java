package com.taskflow.dto;

import com.taskflow.model.Task;
import com.taskflow.model.User;

import java.util.List;

public record UserDto(String name, List<Task> taskCreated, List<Task> taskParticipant) {
    public UserDto(User user) {
        this(user.getName(), user.getTaskCreated(), user.getTaskParticipant());
    }
}
