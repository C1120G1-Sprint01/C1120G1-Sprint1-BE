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

    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.childCategory.category.categoryName like ?1")
    List<Post> findAllByCategoryName(String categoryName);

    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.childCategory.category.categoryName like ?1 " +
            "AND p.childCategory.childCategoryName like ?2")
    List<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName);

}
