package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.ChildCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Integer> {

    @Query(value = "select *" +
            " from child_category" +
            " inner join category on category.category_id=child_category.category_id" +
            " where child_category.child_category_name like concat('%',?1,'%') and category.category_name like concat('%',?2,'%') and child_category.delete_flag = 0", nativeQuery = true)
    List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName, String categoryName);


    @Query(value = "SELECT * FROM ad_web_db.child_category where child_category.delete_flag = 0", nativeQuery = true)
    List<ChildCategory> showAllChildCategory();
}