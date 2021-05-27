package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); //200
    }

}
