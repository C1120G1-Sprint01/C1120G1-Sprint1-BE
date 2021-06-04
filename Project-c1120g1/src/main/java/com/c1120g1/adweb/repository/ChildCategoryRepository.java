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

    @Query(value = "select * from child_category where child_category.category_id = :categoryId", nativeQuery = true)
    List<ChildCategory> findAllByCategoryId(int categoryId);

    //
    @Query(value = "select * from child_category " +
            "where child_category.category_id = ?1",
            nativeQuery = true)
    List<ChildCategory> getAllChildCategoryByCategoryId(Integer id);
}
//
//    //    @Query(value = "select child_category.child_category_name, category.category_name" +
////            " from child_category" +
////            " inner join category on category.category_id=child_category.category_id" +
////            " where (child_category_name like concat('%',?1,'%') and category_name like concat('%',?2,'%')", nativeQuery = true)
//    @Query(value = "select cc from ChildCategory cc " +
//            "where cc.childCategoryName like concat('%',?1,'%') and cc.category.categoryName like concat('%',?2,'%')")
////    @Query(value = "select child_category.child_category_name, category.category_name" +
////            " from child_category" +
////            " inner join category on category.category_id=child_category.category_id" +
////            " where (child_category_name like %?1% and category_name like %?2%)", nativeQuery = true)
//    List<ChildCategory> findAllByChildCategoryNameAndCategoryName(String childCategoryName, String categoryName);
//
//    @Query(value = "select cc from ChildCategory cc " +
//            "where cc.category.categoryName like concat('%',?1,'%')")
////    @Query(value = "select child_category.child_category_name, category.category_name " +
////            "from child_category " +
////            "inner join category on category.category_id=child_category.category_id " +
////            "where (child_category_name like %?1%)", nativeQuery = true)
//    List<ChildCategory> findAllByCategoryName(String categoryName);
//
//    @Query(value = "select cc from ChildCategory cc " +
//            "where cc.childCategoryName like concat('%',?1,'%')")
////    @Query(value = "select child_category.child_category_name, category.category_name " +
////            "from child_category " +
////            "inner join category on category.category_id=child_category.category_id " +
////            "where (category_name like %?1%)", nativeQuery = true)
//    List<ChildCategory> findAllByChildCategoryName(String childCategoryName);
//
//}
//>>>>>>> b0303d3557f91fac1eba4a09e4f2a3cddfd5d7c5
