package com.taskflow.dto;

import com.taskflow.model.Task;
import com.taskflow.model.User;

import java.time.LocalDate;
import java.util.List;

public record TaskResponseDto(
        long id, String description, LocalDate due, Long userId, LocalDate dateCreated,
        LocalDate dateCompleted, List<RequirementResponseDto> requirements, List<Long> participantsId, Long percentageCompleted
) {
    public TaskResponseDto(Task t) {
        this(
                t.getId(), t.getDescription(), t.getDue(), t.getCreator().getId(), t.getDateCreated(), t.getDateCompleted(),
                t.getRequirements().stream().map(RequirementResponseDto::new).toList(),
                t.getParticipants().stream().map(User::getId).toList(), t.getPercentageCompleted()
        );
    }
}
