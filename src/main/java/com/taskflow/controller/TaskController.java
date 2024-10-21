package com.taskflow.controller;

import com.taskflow.dto.TaskResponseDto;
import com.taskflow.exception.ExceptionTaskFlow;
import com.taskflow.model.Task;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Task task){
        try {
            Task t = service.save(task);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (ExceptionTaskFlow e){
            return new ResponseEntity<>("Erro ao criar tarefa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) throws ExceptionTaskFlow {
        Task task = service.findById(id)
                .orElseThrow(()-> new ExceptionTaskFlow("Não foi encontrada tarefa com o id: " + id));
        TaskResponseDto dto = new TaskResponseDto(task);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ExceptionTaskFlow {
        Boolean isDeleted = service.deleteById(id);
        if (!isDeleted){
            throw new ExceptionTaskFlow("Não foi possível deletar tarefa com id: " + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byCreator/{id}")
    public ResponseEntity<List<TaskResponseDto>> getAllByCreator(@PathVariable Long id) throws ExceptionTaskFlow {
       try {
           List<Task> list = service.getAllByUserId(id);
           if (list.isEmpty()) {
               throw new ExceptionTaskFlow("Não foi possivel encontrar uma lista de tarefas para o usuário com id: " + id);
           }
           List<TaskResponseDto> dto = list.stream().map(TaskResponseDto::new).toList();
           return new ResponseEntity<>(dto, HttpStatus.OK);
       }catch (ExceptionTaskFlow e){
           throw new ExceptionTaskFlow("Erro ao tentar encontrar lista: " + e.getMessage());
       }
    }
}
