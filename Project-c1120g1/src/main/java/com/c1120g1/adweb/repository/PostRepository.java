package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * " +
            "from post " +
            "inner join user " +
            "on post.user_id = user.user_id " +
            "where username = ?1 order by status_id", nativeQuery = true)
    Page<Post> findAllByUsername(String username, Pageable pageable);

}
