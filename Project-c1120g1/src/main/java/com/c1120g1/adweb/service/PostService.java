package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {


    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    List<Post> searchByName(String posterName);
}
