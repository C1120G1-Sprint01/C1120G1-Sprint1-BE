package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where user.username = ?1 and post.enabled = true order by post.status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update post " +
            "set description = ?1, email = ?2, " +
            "phone = ?3, post_type = ?4, " +
            "poster_name = ?5, price = ?6, " +
            "title = ?7, child_category_id = ?8, " +
            "status_id = ?9, ward_id = ?10 " +
            "where post_id = ?11", nativeQuery = true)
    void updatePost(String description, String email, String phone, boolean postType, String posterName, int price,
                    String title, int childCategoryId, int statusId, int wardId, int postId);

}
