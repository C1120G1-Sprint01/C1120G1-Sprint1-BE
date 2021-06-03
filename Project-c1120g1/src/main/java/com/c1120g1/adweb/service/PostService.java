package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService{

    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    List<Post> searchByName(String posterName);

    Page<Post> findAllListDetail(Pageable pageable);

    void cancelApprovePost(Integer id);

    Page<Post> findAllListApprove(Pageable pageable);

    void approvePost(Integer id);

    void deletePost(Integer id);

    void deleteById(Integer id);

    void waitPost(Integer id);

    Page<Post> findAllListWait(Pageable pageable);

    Page<Post> findAllByUsername(String username, Pageable pageable);

    Post findByIdAndUserId(Integer id);

    Post findById(Integer id);

    void updatePost(Post post);

    Page<Post> findAllNewest(Pageable pageable);

    void save(Post post);

    List<Post> search(String title, String child_category, String province_name);

    String getPostDateTime();

    Page<Post> findAllByUsernameAndStatusId(String username, Integer statusId, Pageable pageable);


}
