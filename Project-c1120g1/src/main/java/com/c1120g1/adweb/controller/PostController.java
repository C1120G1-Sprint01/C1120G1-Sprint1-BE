package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<Page<Post>> getPostByUserId(@PageableDefault(size = 2) Pageable pageable) {
        String username = "username";
        Page<Post> postList = postService.findAllByUsername(username, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<Post> getByIdAndUserId(@PathVariable("id") Integer id) {
//        Post post = postService.findByIdAndUserId(id);
//        if (post == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(post, HttpStatus.OK);
//    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); //200
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Post>> getPostsByCategoryName(@PathVariable("category") String categoryName) {
        categoryName = categoryName.replace("-", " ");
        List<Post> postList = postService.findAllByCategoryName(categoryName);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("category/{category}/{childCategory}")
    public ResponseEntity<List<Post>> getPostsByCategoryNameAndChildCategoryName(
            @PathVariable("category") String categoryName,
            @PathVariable("childCategory") String childCategoryName) {

        categoryName = categoryName.replace("-", " ");
        childCategoryName = childCategoryName.replace("-", " ");
        List<Post> postList = postService.findAllByCategoryNameAndChildCategoryName(categoryName, childCategoryName);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

}