package com.exercise.servlet1.core;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistentObject implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    public Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
