package com.taskflow;

import com.taskflow.model.User;
import com.taskflow.repository.UserRepo;
import com.taskflow.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo repository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User();
        Mockito.when(repository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        Assertions.assertNotNull(savedUser);
        Mockito.verify(repository, Mockito.times(1)).save(user);
    }

    @Test
    public void testFindUserById_ReturnsUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        Mockito.when(repository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.findUserById(userId);

        // Assert
        Assertions.assertTrue(foundUser.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void testFindUserById_NotFound() {
        // Arrange
        Long userId = 1L;
        Mockito.when(repository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> foundUser = userService.findUserById(userId);

        // Assert
        Assertions.assertFalse(foundUser.isPresent());
        Mockito.verify(repository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void testDeleteUser_Success() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Mockito.when(repository.existsById(user.getId())).thenReturn(true);

        // Act
        Boolean result = userService.deleteUser(user);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(user.getId());
        Mockito.verify(repository, Mockito.times(1)).deleteById(user.getId());
    }

    @Test
    public void testDeleteUser_Failure() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Mockito.when(repository.existsById(user.getId())).thenReturn(false);

        // Act
        Boolean result = userService.deleteUser(user);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(user.getId());
        Mockito.verify(repository, Mockito.never()).deleteById(user.getId());
    }

    @Test
    public void testDeleteById_Success() {
        // Arrange
        Long userId = 1L;
        Mockito.when(repository.existsById(userId)).thenReturn(true);

        // Act
        Boolean result = userService.deleteById(userId);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(userId);
        Mockito.verify(repository, Mockito.times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteById_Failure() {
        // Arrange
        Long userId = 1L;
        Mockito.when(repository.existsById(userId)).thenReturn(false);

        // Act
        Boolean result = userService.deleteById(userId);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(repository, Mockito.times(1)).existsById(userId);
        Mockito.verify(repository, Mockito.never()).deleteById(userId);
    }

    @Test
    public void testExistsById_ReturnsTrue() {
        // Arrange
        Long userId = 1L;
        Mockito.when(repository.existsById(userId)).thenReturn(true);

        // Act
        boolean exists = userService.existsById(userId);

        // Assert
        Assertions.assertTrue(exists);
        Mockito.verify(repository, Mockito.times(1)).existsById(userId);
    }

    @Test
    public void testExistsById_ReturnsFalse() {
        // Arrange
        Long userId = 1L;
        Mockito.when(repository.existsById(userId)).thenReturn(false);

        // Act
        boolean exists = userService.existsById(userId);

        // Assert
        Assertions.assertFalse(exists);
        Mockito.verify(repository, Mockito.times(1)).existsById(userId);
    }
}

