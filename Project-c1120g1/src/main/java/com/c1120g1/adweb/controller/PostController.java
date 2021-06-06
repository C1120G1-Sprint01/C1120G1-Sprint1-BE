package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.service.PostService;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/cus-post-list")
    public ResponseEntity<Page<Post>> getPostByUsername(@PageableDefault(size = 2) Pageable pageable) {
        String username = "username";
        Page<Post> postList = postService.findAllByUsername(username, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/cus-post/{id}")
    public ResponseEntity<Post> getByIdAndUserId(@PathVariable("id") Integer id) {
        Post post = postService.findByIdAndUserId(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); //200
    }

    //    ThuanNN
    @GetMapping("listPost")
    public ResponseEntity<Page<Post>> getAllPost(@PageableDefault(size = 5) Pageable pageable) {
        if (postService.findAllNewest(pageable).isEmpty()) {
            return new ResponseEntity<>(postService.findAllNewest(pageable), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postService.findAllNewest(pageable), HttpStatus.OK);
    }

    //    ThuanNN
    @PostMapping("createPost/{username}")
    public ResponseEntity<Void> createPost(@PathVariable(name = "username") String username,
                                           @RequestBody Post post) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setUser(user);
        postService.save(username, post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    ThuanNN
    @GetMapping("search/{keyword}/{category}/{province}")
    public ResponseEntity<Page<Post>> search(@PathVariable(name = "keyword") String keyword,
                                             @PathVariable(name = "category") String category,
                                             @PathVariable(name = "province") String province,
                                             @PageableDefault(value = 5) Pageable pageable) {
        Page<Post> pagePost = postService.search(keyword, Integer.parseInt(category), Integer.parseInt(province), pageable);
        if (pagePost.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pagePost, HttpStatus.OK);
    }

    //    ViNTT
    @GetMapping("category/{category}")
    public ResponseEntity<Page<Post>> getPostsByCategoryName(@PathVariable("category") String categoryName,
                                                             @PageableDefault(size = 2) Pageable pageable) {
        Page<Post> postList = postService.findAllByCategoryName(categoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    //    ViNTT
    @GetMapping("category/{category}/{childCategory}")
    public ResponseEntity<Page<Post>> getPostsByCategoryNameAndChildCategoryName(
            @PathVariable("category") String categoryName,
            @PathVariable("childCategory") String childCategoryName,
            @PageableDefault(size = 2) Pageable pageable) {
        Page<Post> postList = postService.findAllByCategoryNameAndChildCategoryName(categoryName, childCategoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
