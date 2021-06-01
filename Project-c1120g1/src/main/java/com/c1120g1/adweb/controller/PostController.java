package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    @GetMapping("/search")
    public List<Post> searchByName(String posterName){
        return postService.searchByName(posterName);
    }
}
