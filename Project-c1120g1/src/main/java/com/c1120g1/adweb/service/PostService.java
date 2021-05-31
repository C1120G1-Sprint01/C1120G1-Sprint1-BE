package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Page<Post> findAllListDetail(Pageable pageable);
    Post findById(Integer id);
    Page<Post> findAllListApprove(Pageable pageable);
    void approvePost(Integer id);
    void deleteById(Integer id);
    void save(Post post);
    void waitPost(Integer id);
    Page<Post> findAllListWait(Pageable pageable);
}
