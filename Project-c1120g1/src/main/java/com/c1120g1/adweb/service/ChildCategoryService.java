package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.ChildCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ChildCategoryService {

    Page<ChildCategory> findAllChildCategoryPage(Pageable pageable);
    List<ChildCategory> findAllChildCategory();
    ChildCategory findChildCategoryById(Integer id);
    void save(ChildCategory childCategory);
    void delete(Integer id);
    List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName,String categoryName);
    List<ChildCategory> findAllByCategoryName(String categoryName);
    List<ChildCategory> findAllByChildCategoryName(String childCategoryName);

}
