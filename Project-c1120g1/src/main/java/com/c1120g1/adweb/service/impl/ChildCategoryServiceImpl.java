package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.ChildCategory;
import com.c1120g1.adweb.repository.ChildCategoryRepository;
import com.c1120g1.adweb.service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildCategoryServiceImpl implements ChildCategoryService {

    @Autowired
    private ChildCategoryRepository repository;

    @Override
    public Page<ChildCategory> findAllChildCategory(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ChildCategory> findAllChildCategory(int index) {
        return repository.findAllChildCategory(index);
    }

    @Override
    public List<ChildCategory> findAllChildCategory() {
        return repository.findAll();
    }

    @Override
    public ChildCategory findChildCategoryById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(ChildCategory childCategory) {
        repository.save(childCategory);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName,String categoryName) {
        return repository.findAllByChildCategoryNameAndCategoryName(childCategoryName,categoryName);
    }

    @Override
    public List<ChildCategory> findAllByCategoryName(String categoryName) {
        return repository.findAllByCategoryName(categoryName);
    }

    @Override
    public List<ChildCategory> findAllByChildCategoryName(String childCategoryName) {
        return repository.findAllByChildCategoryName(childCategoryName);
    }
}
