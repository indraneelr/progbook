package com.progbook.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String uuid;

    @Column
    private String version;

    public Language(long id, String uuid, String description, String name, String version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uuid = uuid;
        this.version = version;
    }

    public Language() {
    }

    public Language(long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
