package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;

import java.util.List;

public interface PostService {


    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    List<Post> searchByName(String posterName);
}
