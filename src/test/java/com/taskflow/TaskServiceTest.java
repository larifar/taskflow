package com.taskflow;
import com.taskflow.model.Task;
import com.taskflow.repository.TaskRepo;
import com.taskflow.service.TaskService;
import com.taskflow.service.UserService;
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
public class TaskServiceTest {

    @Mock
    private TaskRepo repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testSaveTask() {
        // Arrange
        Task task = new Task();
        Mockito.when(repository.save(task)).thenReturn(task);

        // Act
        Task savedTask = taskService.save(task);

        // Assert
        Assertions.assertNotNull(savedTask);
        Mockito.verify(repository, Mockito.times(1)).save(task);
    }

    @Test
    public void testFindById_ReturnsTask() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task();
        Mockito.when(repository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        Optional<Task> foundTask = taskService.findById(taskId);

        // Assert
        Assertions.assertTrue(foundTask.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(taskId);
    }

    @Test
    public void testFindById_NotFound() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(repository.findById(taskId)).thenReturn(Optional.empty());

        // Act
        Optional<Task> foundTask = taskService.findById(taskId);

        // Assert
        Assertions.assertFalse(foundTask.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(taskId);
    }

    @Test
    public void testGetAllByUserId_UserExists() {
        // Arrange
        Long userId = 1L;
        List<Task> tasks = List.of(new Task());
        Mockito.when(userService.existsById(userId)).thenReturn(true);
        Mockito.when(repository.getTasksByCreatorId(userId)).thenReturn(Optional.of(tasks));

        // Act
        List<Task> result = taskService.getAllByUserId(userId);

        // Assert
        Assertions.assertFalse(result.isEmpty());
        Mockito.verify(userService, Mockito.times(1)).existsById(userId);
        Mockito.verify(repository, Mockito.times(1)).getTasksByCreatorId(userId);
    }

    @Test
    public void testGetAllByUserId_UserNotExists() {
        // Arrange
        Long userId = 1L;
        Mockito.when(userService.existsById(userId)).thenReturn(false);

        // Act
        List<Task> result = taskService.getAllByUserId(userId);

        // Assert
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(userService, Mockito.times(1)).existsById(userId);
        Mockito.verify(repository, Mockito.never()).getTasksByCreatorId(userId);
    }

    @Test
    public void testExistsById_ReturnsTrue() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(repository.existsById(taskId)).thenReturn(true);

        // Act
        boolean exists = taskService.existsById(taskId);

        // Assert
        Assertions.assertTrue(exists);
        Mockito.verify(repository, Mockito.times(1)).existsById(taskId);
    }

    @Test
    public void testExistsById_ReturnsFalse() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(repository.existsById(taskId)).thenReturn(false);

        // Act
        boolean exists = taskService.existsById(taskId);

        // Assert
        Assertions.assertFalse(exists);
        Mockito.verify(repository, Mockito.times(1)).existsById(taskId);
    }

    @Test
    public void testDeleteById_Success() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(repository.existsById(taskId)).thenReturn(true);

        // Act
        boolean result = taskService.deleteById(taskId);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(taskId);
        Mockito.verify(repository, Mockito.times(1)).deleteById(taskId);
    }

    @Test
    public void testDeleteById_Failure() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(repository.existsById(taskId)).thenReturn(false);

        // Act
        boolean result = taskService.deleteById(taskId);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(taskId);
        Mockito.verify(repository, Mockito.never()).deleteById(taskId);
    }
}

