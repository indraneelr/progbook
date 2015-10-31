package com.progbook.representation.query;

import com.progbook.persistence.model.Person;

public class PersonRepresentation {
    private Long id;
    private String name;
    private String email;
    private String uuid;

    public PersonRepresentation() {
    }

    public PersonRepresentation(Person person) {
        if (person == null) return;
        id = person.getId();
        uuid = person.getUuid();
        name = person.getName();
        email = person.getEmail();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
