package com.taskflow.dto;

import com.taskflow.model.Requirement;
import com.taskflow.model.User;

import java.time.LocalDate;
import java.util.List;

public record RequirementResponseDto(
        Long id, String description, LocalDate dateCompleted, Boolean isCompleted, List<User> participants, Long taskId
) {
    public RequirementResponseDto(Requirement r){
        this(r.getId(), r.getDescription(), r.getDateCompleted(), r.getCompleted(), r.getParticipants(), r.getTask().getId());
    }
}
