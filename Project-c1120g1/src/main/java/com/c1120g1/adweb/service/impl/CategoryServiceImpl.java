package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.repository.CategoryRepository;
import com.c1120g1.adweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> findAllCategory() {
        return repository.showAllCategory();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void saveCategory(Category category) {
         category.setDeleteFlag(true);
        repository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        category.setDeleteFlag(false);
        repository.save(category);
    }

    @Override
    public void addCategory(Category category) {
        category.setDeleteFlag(true);
        repository.save(category);
    }

    @Override
    public List<Category> findAllCategoryByCategoryName(String categoryName) {
        return repository.findAllCategoryByCategoryName(categoryName);
    }

    @Override
    public void checkDup(Category category, Errors errors) {
        for(Category cate : findAllCategory()){
            if (cate.getCategoryName().equals(category.getCategoryName())){
                errors.rejectValue("categoryName", "checkDupCategory");
                return;
            }
        }
    }

    @Override
    public List<Category> searchAllCategory(String categoryName) {
        return repository.searchAllCategory(categoryName);
    }
}
