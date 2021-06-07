package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Category;
import org.springframework.validation.Errors;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<Category> findAllCategory();

    Category findCategoryById(Integer id);

    void saveCategory(Category category);

    void deleteCategory(Category category);

    void addCategory(Category category);

    List<Category> findAllCategoryByCategoryName(String categoryName);

    void checkDup (Category category, Errors errors);

    List<Category> searchAllCategory(String categoryName);
}
