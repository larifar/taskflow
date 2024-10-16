package com.taskflow.repository;

import com.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
