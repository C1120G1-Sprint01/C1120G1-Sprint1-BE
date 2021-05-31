package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Post findById(Integer id);

    void deleteById(Integer id);

    Page<Post> findAllByUsername(String username, Pageable pageable);

    Post findByIdAndUserId(Integer id);

    Page<Post> findAllNewest(Pageable pageable);

    void save(Post post);

    List<Post> search(String title, String child_category, String province_name);

    //    List<Post> statisticalByDate(String startDate, String endDate);
}
