package com.todoTask.crud.repaso.dto.request;

import com.todoTask.crud.repaso.tools.enums.RoleEnum;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

//    DATA IF THE USER WILL BE A PTIENT OR A DOCTOR

    private String name;

    private String lastname;

    private String document;

    private Long specialty_id;

    private String username;

    private String password;

    private String email;

    private Boolean active = true;

    private Boolean isAdmin = false;

    private Boolean isDoctor = false;

    private Set<RoleEnum> roles = new HashSet<>();

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public UserDTO() {
    }

    public UserDTO(Boolean active, String email, Boolean isAdmin, String password, Boolean isDoctor, String username) {
        this.active = active;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
        this.isDoctor = isDoctor;
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getDoctor() {
        return isDoctor;
    }

    public void setDoctor(Boolean doctor) {
        isDoctor = doctor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(Long specialty_id) {
        this.specialty_id = specialty_id;
    }
}


