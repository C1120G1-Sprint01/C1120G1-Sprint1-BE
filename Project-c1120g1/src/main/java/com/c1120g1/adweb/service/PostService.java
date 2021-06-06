package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<Post> findAllByUsername(String username, Pageable pageable);

    Post findByIdAndUserId(Integer id);

    Post findById(Integer id);

    Page<Post> findAllNewest(Pageable pageable);

    //    ThuanNN
    void save(String username, Post post);

    //    ThuanNN
    Page<Post> search(String keyword, Integer category, Integer province, Pageable pageable);

    String getPostDateTime();

//    ViNTT
    Page<Post> findAllByCategoryName(String categoryName, Pageable pageable);
    Page<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable);
}
