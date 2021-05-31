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
    public ChildCategory findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
