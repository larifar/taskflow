package com.taskflow.controller;

import com.taskflow.dto.RequirementResponseDto;
import com.taskflow.exception.ExceptionTaskFlow;
import com.taskflow.model.Requirement;
import com.taskflow.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    private RequirementService service;

    @PostMapping
    public ResponseEntity<RequirementResponseDto> save(@RequestBody @Valid Requirement requirement){
        Requirement req = service.save(requirement);
        RequirementResponseDto dto = new RequirementResponseDto(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<RequirementResponseDto>> getAllbyTaskId(@PathVariable Long id) throws ExceptionTaskFlow {
        List<Requirement> list = service.getAllByTask(id);
        if (list.isEmpty()){
            throw new ExceptionTaskFlow("Não foi possível encontrar requisitos para tarefa de id: " + id);
        }
        List<RequirementResponseDto> dto = list.stream().map(RequirementResponseDto::new).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequirementResponseDto> getById(@PathVariable Long id) throws ExceptionTaskFlow {
        Requirement req = service.findById(id)
                .orElseThrow(() -> new ExceptionTaskFlow("Não foi possível encontrar um requisito com esse id: " + id));
        RequirementResponseDto dto = new RequirementResponseDto(req);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws ExceptionTaskFlow {
        Boolean deleted = service.deleteById(id);
        if (!deleted){
            throw new ExceptionTaskFlow("Não foi encontrado requisito com esse id: " + id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
