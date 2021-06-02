package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/cus-post-list")
    public ResponseEntity<Page<Post>> getPostByUsername(@RequestParam String username,
                                                        @PageableDefault(size = 2) Pageable pageable) {
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

    @PostMapping("/cus-post-edit/{id}")
    public ResponseEntity<Post> editPost(@Valid @RequestBody Post post, BindingResult bindingResult, @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Post postObj = postService.findByIdAndUserId(id);

            if (postObj == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                if (post.getStatus().getStatusId() == 2 || post.getStatus().getStatusId() == 4 ||
                        post.getStatus().getStatusId() == 5) {
                    postObj.setDescription(post.getDescription());
                    postObj.setEmail(post.getEmail());
                    postObj.setPhone(post.getPhone());
                    postObj.setPostType(post.isPostType());
                    postObj.setPosterName(post.getPosterName());
                    postObj.setPrice(post.getPrice());
                    postObj.setTitle(post.getTitle());
                    postObj.setChildCategory(post.getChildCategory());
                    postObj.setStatus(post.getStatus());
                    postObj.setWard(post.getWard());
//                postObj.setImageSet(post.getImageSet());
                    postService.updatePost(postObj);
                    return new ResponseEntity<>(postObj, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

            }
        }

    }

}