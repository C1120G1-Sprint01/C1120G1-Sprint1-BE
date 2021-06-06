package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM ad_web_db.category where category.delete_flag = 1", nativeQuery = true)
    List<Category> showAllCategory();
}
