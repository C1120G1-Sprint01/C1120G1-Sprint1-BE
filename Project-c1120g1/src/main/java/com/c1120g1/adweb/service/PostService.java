package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Page<Post> findAllByUsername(String username, Pageable pageable);

    List<Post> findAllByCategoryName(String categoryName);

    List<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName);

    Post findByIdAndUserId(Integer id);

    Post findById(Integer id);

}
