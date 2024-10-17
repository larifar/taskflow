package com.taskflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.beans.Encoder;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String name;
    @NotBlank(message = "Senha não pode ser nula")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String password;
    @NotBlank(message = "Email não pode ser nulo")
    @Email(message = "Email não válido.")
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
