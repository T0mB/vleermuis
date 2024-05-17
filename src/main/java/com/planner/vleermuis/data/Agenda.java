package com.planner.vleermuis.data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @Column(name="id", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
    private List<Activity> activities = new ArrayList<>();

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
}
