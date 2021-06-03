package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<Category> findAllCategory();
    Category findCategoryById(Integer id);
    void save(Category category);
    void delete(Integer id);

}
