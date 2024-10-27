package com.jwt.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String userRole;
    private String description;

    public String getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(String role) {
        this.userRole = role;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserRoleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}