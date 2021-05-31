package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.ChildCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Integer> {

    @Query(value =  "select * from child_category " +
                    "where child_category.category_id = ?1",
            nativeQuery = true)
    List<ChildCategory> getAllChildCategoryByCategoryId(Integer id);
}
