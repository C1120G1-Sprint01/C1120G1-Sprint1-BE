package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.ChildCategory;
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

}
