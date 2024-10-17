package com.taskflow.repository;

import com.taskflow.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepo extends JpaRepository<Requirement, Long> {
    Optional<List<Requirement>> getRequirementsByTaskId(Long id);
}
