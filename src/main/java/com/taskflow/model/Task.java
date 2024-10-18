package com.taskflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "A tarefa precisa de uma descrição.")
    private String description;
    @NotNull(message = "A tarefa precisa de um prazo.")
    private LocalDate due;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "A tarefa precisa estar associada a um usuário.")
    private User creator;
    @Column(name = "date_created", nullable = false)
    @NotNull(message = "A tarefa precisa ter uma data de criação.")
    private LocalDate dateCreated;
    @Column(name = "date_completed")
    private LocalDate dateCompleted;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Requirement> requirements;
    @ManyToMany
    @JoinTable(
            name = "task_participants",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;

    @Column(name = "percentage_completed")
    private Long percentageCompleted;

    //get and set


    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
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

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public Long getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(Long percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }
}
