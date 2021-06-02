package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.repository.ChildCategoryRepository;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildCategoryServiceImpl implements ChildCategoryService {

    @Autowired
    private ChildCategoryRepository repository;

    @Override
    public List<ChildCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ChildCategory> findAllByCategoryId(int categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public ChildCategory findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ChildCategory> getAllChildCategoryByCategoryId(Integer id) {
        return repository.getAllChildCategoryByCategoryId(id);
    }

    @Override
    public List<ChildCategory> findAllChildCategory() {
        return repository.showAllChildCategory();
    }

    @Override
    public ChildCategory findChildCategoryById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void addChildCategory(ChildCategory childCategory) {
        childCategory.setDeleteFlag(false);
        repository.save(childCategory);
    }

    @Override
    public void saveChildCategory(ChildCategory childCategory) {
        repository.save(childCategory);
    }

    @Override
    public void deleteChildCategory(ChildCategory childCategory) {
        childCategory.setDeleteFlag(true);
        repository.save(childCategory);
    }

    @Override
    public List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName, String categoryName) {
        return repository.findAllByChildCategoryNameAndCategoryName(childCategoryName,categoryName);
    }
}
