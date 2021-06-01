package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    @Query(value = "select * from post where poster_name = :#{#posterName}", nativeQuery = true)
    List<Post> searchByName(String posterName);

}
