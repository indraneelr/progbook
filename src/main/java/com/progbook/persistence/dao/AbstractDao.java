package com.progbook.persistence.dao;

import java.util.List;

public interface AbstractDao<T> {

    void save(T entity);

    T fetchById(long id);

    T fetchByUuid(String uuid);

    List<T> fetchAll();

    void delete(T entity);
}
