package com.planner.vleermuis.data;

import jakarta.persistence.*;

@Entity
@Table(name="source_site")
public class SourceSite {

    @Id
    @Column(name="id", unique=true, updatable=false, nullable=false)
    @GeneratedValue
    private long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="link", nullable = false)
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
