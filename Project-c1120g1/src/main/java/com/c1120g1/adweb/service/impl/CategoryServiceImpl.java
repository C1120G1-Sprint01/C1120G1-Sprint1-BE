package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.repository.CategoryRepository;
import com.c1120g1.adweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Override
    public List<Category> findAllCategory() {
        return repository.findAll();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }


}
