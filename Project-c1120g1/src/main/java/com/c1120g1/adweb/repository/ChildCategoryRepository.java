package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.ChildCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Integer> {
    //    @Query(value = "select child_category.child_category_name, category.category_name" +
//            " from child_category" +
//            " inner join category on category.category_id=child_category.category_id" +
//            " where (child_category_name like concat('%',?1,'%') and category_name like concat('%',?2,'%')", nativeQuery = true)
    @Query(value = "select cc from ChildCategory cc " +
            "where cc.childCategoryName like concat('%',?1,'%') and cc.category.categoryName like concat('%',?2,'%')")
    List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName, String categoryName);

    @Query(value = "select cc from ChildCategory cc " +
            "where cc.category.categoryName like concat('%',?1,'%')")
    List<ChildCategory> findAllByCategoryName(String categoryName);

    @Query(value = "select cc from ChildCategory cc " +
            "where cc.childCategoryName like concat('%',?1,'%')")
    List<ChildCategory> findAllByChildCategoryName(String childCategoryName);

    @Query(value = "select child_category.child_category_name,category.category_name" +
            " from child_category" +
            " inner join category on category.category_id=child_category.category_id" +
            " group by child_category.child_category_id limit 1,5",nativeQuery = true)
    List<ChildCategory> findAllChildCategory(int index);
}