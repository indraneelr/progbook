package com.progbook.service;

import com.progbook.persistence.model.Person;
import com.progbook.persistence.model.QuestionTag;

import java.util.Set;

public interface PersonService {
    Person fetchById(String id);
    Person fetchByName(String personName);

    void save(Person person);
    void delete(String id);
}
