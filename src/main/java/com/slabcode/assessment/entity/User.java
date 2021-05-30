package com.slabcode.assessment.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 4, message = "Minimum password length: 4 characters")
    private String passWord;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}