package com.taskflow.service;

import com.taskflow.model.User;
import com.taskflow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repository;

    public User saveUser(User user){
        return repository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    public Boolean deleteUser(User user){
        if (repository.existsById(user.getId())) {
            repository.deleteById(user.getId());
            return true;
        }
        return false;
    }

    public Boolean deleteById(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
