package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

//    -----------------------LIST DETAIL------------------------

    @GetMapping("/listDetail")
    public ResponseEntity<Page<Post>> getListPostDetail(Pageable pageable) {
        Page<Post> listPostDetail = postService.findAllListDetail(pageable);
        if (listPostDetail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listPostDetail, HttpStatus.OK);
    }

    @GetMapping(value = "/listDetail/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostDetail(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //    -----------------------LIST APPROVE------------------------

    @GetMapping("/listApprove")
    public ResponseEntity<Page<Post>> getListPostApprove(Pageable pageable) {
        Page<Post> listPostApprove = postService.findAllListApprove(pageable);
        if (listPostApprove.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listPostApprove, HttpStatus.OK);
    }

    @GetMapping(value = "/listApprove/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostApprove(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


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

    @PutMapping("/listApprove/approve/{postId}")
    public ResponseEntity<Post> approvePost(@PathVariable("postId") Integer postId) {
        Post currentPost = this.postService.findById(postId);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.approvePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/listApprove/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/listApprove/wait/{postId}")
    public ResponseEntity<Post> waitPost(@PathVariable("postId") Integer postId) {
        Post currentPost = this.postService.findById(postId);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.waitPost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    -----------------------LIST WAIT------------------------

    @GetMapping("/listWait")
    public ResponseEntity<Page<Post>> getListPostWait(Pageable pageable) {
        Page<Post> listPostWait = postService.findAllListWait(pageable);
        if (listPostWait.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listPostWait, HttpStatus.OK);
    }

    @GetMapping(value = "/listWait/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostWait(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/listWait/approve/{postId}")
    public ResponseEntity<Post> approveWait(@PathVariable("postId") Integer postId) {
        Post currentPost = this.postService.findById(postId);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.approvePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/listWait/delete/{postId}")
    public ResponseEntity<Post> deleteWait(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //    -----------------------END OF QUANG------------------------

    /**
     * Author: ViNTT
     * Get data for Post Details Page
     */
    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer postId) {
        Post post = postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); // 200
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

    @GetMapping("listPost")
    public ResponseEntity<Page<Post>> getAllPost(@PageableDefault(size = 5) Pageable pageable) {
        if (postService.findAllNewest(pageable).isEmpty()) {
            return new ResponseEntity<Page<Post>>(postService.findAllNewest(pageable), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Post>>(postService.findAllNewest(pageable), HttpStatus.OK);
    }

    @PostMapping("createPost")
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("search")
    public List<Post> search(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "child_category") String child_category,
            @RequestParam(name = "province") String province) {
        return postService.search("%" + title + "%", child_category, province);
    }

    /**
     * Author: ViNTT
     * Get data for List Post By Category Page
     */
    @GetMapping("category/{category}")
    public ResponseEntity<Page<Post>> getPostsByCategoryName(@PathVariable("category") String categoryName,
                                                             @PageableDefault(size = 2) Pageable pageable) {
        Page<Post> postList = postService.findAllByCategoryName(categoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * Author: ViNTT
     * Get data for List Post By Child Category Page
     */
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

