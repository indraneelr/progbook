package com.progbook.service;

import com.progbook.persistence.model.Category;
import java.util.Set;

public interface CategoryService {
    Category fetchById(String id);
    Set<Category> fetchAll();

    void save(Category category);
    void delete(String id);

}
