package com.taskflow.controller;

import com.taskflow.dto.UserDto;
import com.taskflow.exception.ExceptionTaskFlow;
import com.taskflow.model.User;
import com.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @ResponseBody
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user){
        User userSaved = service.saveUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping({"/{id}"})
    public ResponseEntity<UserDto> getById(@PathVariable Long id) throws ExceptionTaskFlow {
        User user = service.findUserById(id).orElseThrow(() -> new ExceptionTaskFlow("Usuário com id não encontrado."));
        UserDto dto = new UserDto(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ExceptionTaskFlow {
        Boolean deleted = service.deleteById(id);
        if (!deleted){
            throw new ExceptionTaskFlow("Usuário com id não encontrado");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
