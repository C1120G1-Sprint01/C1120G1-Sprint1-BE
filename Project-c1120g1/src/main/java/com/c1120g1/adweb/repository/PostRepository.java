package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT e FROM Post e")
    Page<Post> findAll(Pageable pageable);

//    List<Post> statisticalByDate(String startDate, String endDate);
}
