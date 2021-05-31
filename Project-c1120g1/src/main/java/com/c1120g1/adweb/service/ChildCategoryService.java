package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.ChildCategory;

import java.util.List;

public interface ChildCategoryService {
    List<ChildCategory> findAll();

    ChildCategory findById(Integer id);
}
