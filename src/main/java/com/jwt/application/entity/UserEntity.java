package com.jwt.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    private Integer age;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserRoleEntity userRole;

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public UserEntity setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserEntity setActive(boolean active) {
        isActive = active;
        return this;
    }


    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserEntity setCreatedAt(LocalDateTime created) {
        this.createdAt = created;
        return this;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public UserEntity setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

}