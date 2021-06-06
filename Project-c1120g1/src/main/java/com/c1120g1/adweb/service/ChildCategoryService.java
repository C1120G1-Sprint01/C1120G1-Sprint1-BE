package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Category;
import com.c1120g1.adweb.entity.ChildCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.Errors;

import java.util.List;

public interface ChildCategoryService {

    List<ChildCategory> findAll();

    List<ChildCategory> findAllByCategoryId(int categoryId);

    ChildCategory findById(Integer id);

    List<ChildCategory> getAllChildCategoryByCategoryId(Integer id);

    List<ChildCategory> findAllChildCategory();

    ChildCategory findChildCategoryById(Integer id);

    void addChildCategory(ChildCategory childCategory);

    void saveChildCategory(ChildCategory childCategory);

    void deleteChildCategory(ChildCategory childCategory);

    List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName, String categoryName);

    void checkDup (ChildCategory childCategory, Errors errors);

    List<ChildCategory> searchAllChildCategory(String childCategoryName, Integer categoryId);
}
