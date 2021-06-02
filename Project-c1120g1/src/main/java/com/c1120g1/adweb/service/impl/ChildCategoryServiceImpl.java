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
<<<<<<< HEAD
=======
    public List<ChildCategory> findAllByCategoryId(int categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public Page<ChildCategory> findAllChildCategoryPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
>>>>>>> b0303d3557f91fac1eba4a09e4f2a3cddfd5d7c5
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
<<<<<<< HEAD
=======

    @Override
    public List<ChildCategory> findAllByCategoryName(String categoryName) {
        return repository.findAllByCategoryName(categoryName);
    }

    @Override
    public List<ChildCategory> findAllByChildCategoryName(String childCategoryName) {
        return repository.findAllByChildCategoryName(childCategoryName);

    }
>>>>>>> b0303d3557f91fac1eba4a09e4f2a3cddfd5d7c5
}
