package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Page<Post> findAllByUsername(String username, Pageable pageable);

    Post findByIdAndUserId(Integer id);
}
