package com.planner.vleermuis.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @Column(name="id", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="at_date", nullable = false)
    private Date atDate;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="agenda_id", nullable = false)
    protected Agenda agenda;

    public Agenda getAgenda() {return agenda;};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAtDate() {
        return atDate;
    }

    public void setAtDate(Date atDate) {
        this.atDate = atDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
