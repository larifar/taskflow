package com.taskflow.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "creator")
    private List<Task> taskCreated;
    @ManyToMany(mappedBy = "participants")
    private List<Task> taskParticipant;

    //get and set

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTaskCreated() {
        return taskCreated;
    }

    public void setTaskCreated(List<Task> taskCreated) {
        this.taskCreated = taskCreated;
    }

    public List<Task> getTaskParticipant() {
        return taskParticipant;
    }

    public void setTaskParticipant(List<Task> taskParticipant) {
        this.taskParticipant = taskParticipant;
    }
}
