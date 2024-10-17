package com.taskflow;

import com.taskflow.model.Requirement;
import com.taskflow.repository.RequirementRepo;
import com.taskflow.service.RequirementService;
import com.taskflow.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RequirementServiceTest {

    @Mock
    private RequirementRepo repository;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private RequirementService requirementService;

    @Test
    public void testSaveRequirement() {
        // Arrange
        Requirement requirement = new Requirement();
        Mockito.when(repository.save(requirement)).thenReturn(requirement);

        // Act
        Requirement savedRequirement = requirementService.save(requirement);

        // Assert
        Assertions.assertNotNull(savedRequirement);
        Mockito.verify(repository, Mockito.times(1)).save(requirement);
    }

    @Test
    public void testFindById_ReturnsRequirement() {
        // Arrange
        Long requirementId = 1L;
        Requirement requirement = new Requirement();
        Mockito.when(repository.findById(requirementId)).thenReturn(Optional.of(requirement));

        // Act
        Optional<Requirement> foundRequirement = requirementService.findById(requirementId);

        // Assert
        Assertions.assertTrue(foundRequirement.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(requirementId);
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        Long requirementId = 1L;
        Mockito.when(repository.findById(requirementId)).thenReturn(Optional.empty());

        // Act
        Optional<Requirement> foundRequirement = requirementService.findById(requirementId);

        // Assert
        Assertions.assertFalse(foundRequirement.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(requirementId);
    }

    @Test
    public void testGetAllByTask_TaskExists() {
        // Arrange
        Long taskId = 1L;
        List<Requirement> requirements = List.of(new Requirement());
        Mockito.when(taskService.existsById(taskId)).thenReturn(true);
        Mockito.when(repository.getRequirementsByTaskId(taskId)).thenReturn(Optional.of(requirements));

        // Act
        List<Requirement> result = requirementService.getAllByTask(taskId);

        // Assert
        Assertions.assertFalse(result.isEmpty());
        Mockito.verify(taskService, Mockito.times(1)).existsById(taskId);
        Mockito.verify(repository, Mockito.times(1)).getRequirementsByTaskId(taskId);
    }

    @Test
    public void testGetAllByTask_TaskNotExists() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(taskService.existsById(taskId)).thenReturn(false);

        // Act
        List<Requirement> result = requirementService.getAllByTask(taskId);

        // Assert
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(taskService, Mockito.times(1)).existsById(taskId);
        Mockito.verify(repository, Mockito.never()).getRequirementsByTaskId(taskId);
    }

    @Test
    public void testDeleteById_Success() {
        // Arrange
        Long requirementId = 1L;
        Mockito.when(repository.existsById(requirementId)).thenReturn(true);

        // Act
        Boolean result = requirementService.deleteById(requirementId);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(requirementId);
        Mockito.verify(repository, Mockito.times(1)).deleteById(requirementId);
    }

    @Test
    public void testDeleteById_Failure() {
        // Arrange
        Long requirementId = 1L;
        Mockito.when(repository.existsById(requirementId)).thenReturn(false);

        // Act
        Boolean result = requirementService.deleteById(requirementId);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(requirementId);
        Mockito.verify(repository, Mockito.never()).deleteById(requirementId);
    }
}

