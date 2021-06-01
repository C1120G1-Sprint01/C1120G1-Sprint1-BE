package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    @GetMapping("/search/{posterName}")
    public List<Post> searchByName(@PathVariable("posterName") String posterName){
        return postService.searchByName(posterName);
    }
}
