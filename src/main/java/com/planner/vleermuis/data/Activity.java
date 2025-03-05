package com.planner.vleermuis.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @Column(name="id", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="at_date", nullable = false)
    private LocalDateTime atDate;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="agenda_id", nullable = false)
    protected Agenda agenda;

    public Month getMonthOfActivity(){
        return atDate.getMonth();
    }

    public Agenda getAgenda() {return agenda;}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getAtDate() {
        return atDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAtDate(LocalDateTime atDate) {
        this.atDate = atDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }


}
