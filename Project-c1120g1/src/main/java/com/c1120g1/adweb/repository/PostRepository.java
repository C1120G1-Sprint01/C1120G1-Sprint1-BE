package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where username = ?1 order by status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

//    ThuanNN
    @Query(value = "select * from post " +
            "inner join ward on post.ward_id = ward.ward_id " +
            "inner join district on district.district_id = ward.district_id " +
            "inner join province on province.province_id = district.province_id " +
            "inner join child_category on child_category.child_category_id = post.child_category_id " +
            "inner join category on category.category_id = child_category.category_id " +
            "where (  " +
            "        (post.title like '%'+?1+'%') &&  " +
            "        (category.category_id = ?2) &&  " +
            "        (province.province_id = ?3)  " +
            "       )" +
            "order by post.post_date_time desc ",
            nativeQuery = true)
    Page<Post> search(String keyword, Integer category, Integer province, Pageable pageable);

//    ThuanNN
    @Query(value="select * from post where enabled = 1 order by post_date_time desc", nativeQuery = true)
    Page<Post> findAllNewest(Pageable pageable);

//    ViNTT
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name like ?1", nativeQuery = true)
    Page<Post> findAllByCategoryName(String categoryName, Pageable pageable);

//    ViNTT
    @Query(value = "SELECT * FROM post " +
            "INNER JOIN child_category on post.child_category_id = child_category.child_category_id " +
            "INNER JOIN category on child_category.category_id = category.category_id " +
            "WHERE category.category_name like ?1 AND child_category.child_category_name like ?2", nativeQuery = true)
    Page<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);
}
