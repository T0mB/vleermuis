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

    public SourceSite(String name, String link) {
        this.name = name;
        this.link = link;
    }

    /**
     * only here so app won't complain
     */
    public SourceSite(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getLink() {
        return link;
    }

}
