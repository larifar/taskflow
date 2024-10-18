package com.taskflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotNull(message = "O requisito deve ter uma descrição.")
    private String description;
    @Column(name = "date_completed")
    private LocalDate dateCompleted;
    private Boolean isCompleted;
    @OneToMany
    private List<User> participants;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @NotNull(message = "O requisito deve estar associado a uma tarefa.")
    private Task task;

    //get and set


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> users) {
        this.participants = users;
    }
}
