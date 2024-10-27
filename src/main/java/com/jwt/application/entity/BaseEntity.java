package com.jwt.application.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    public BaseEntity()
    {
    }


    public BaseEntity(String id)
    {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }
}