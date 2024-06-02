package com.planner.vleermuis.data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Month;

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
    private LocalDate atDate;

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

    public LocalDate getAtDate() {
        return atDate;
    }

    public String getDescription() {
        return description;
    }

}
