package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.PostDTO;
import com.c1120g1.adweb.dto.PostStatisticDTO;
import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.service.AccountService;
import com.c1120g1.adweb.service.PostService;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService postService;


    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    /**
     * author: ThinhTHB
     * method: search post by name
     */
    @GetMapping("/search/{posterName}")
    public List<Post> searchByName(@PathVariable("posterName") String posterName) {
        return postService.searchByName(posterName);
    }

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

    @GetMapping("/listDetail/searchByTitle")
    public ResponseEntity<Page<Post>> searchByTitle(@RequestParam String title, Pageable pageable) {
        Page<Post> listPostDetail = this.postService.searchByTitle(title, pageable);
        if (listPostDetail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listPostDetail, HttpStatus.OK);
    }

    @PutMapping("/listDetail/cancelApprove/{postId}")
    public ResponseEntity<Post> cancelApprovePost(@PathVariable("postId") Integer postId) {
        Post currentPost = this.postService.findById(postId);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.cancelApprovePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
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
                                                        @PageableDefault(size = 2) Pageable pageable,
                                                        @RequestParam Optional<Integer> statusId) {
        try {
            Page<Post> postList;
            if (statusId.isPresent()) {
                postList = postService.findAllByUsernameAndStatusId(username, statusId.get(), pageable);
            } else {
                postList = postService.findAllByUsername(username, pageable);
            }
            if (postList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        User userApprove = currentPost.getUser();
        if (userApprove != null) {
            String toEmail = userApprove.getEmail();
            this.accountService.sendEmailApprove(toEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/listApprove/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.deletePost(postId);
        User userDelete = post.getUser();
        if (userDelete != null) {
            String toEmail = userDelete.getEmail();
            this.accountService.sendEmailDelete(toEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
        User userApprove = currentPost.getUser();
        if (userApprove != null) {
            String toEmail = userApprove.getEmail();
            this.accountService.sendEmailApprove(toEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/listWait/delete/{postId}")
    public ResponseEntity<Post> deleteWait(@PathVariable("postId") Integer postId) {
        Post post = this.postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postService.deletePost(postId);
        User userDelete = post.getUser();
        if (userDelete != null) {
            String toEmail = userDelete.getEmail();
            this.accountService.sendEmailDelete(toEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    -----------------------END OF QUANG------------------------

    @PostMapping("/cus-post-edit")
    public ResponseEntity<PostDTO> editPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                if (postDTO.getPost().getStatus().getStatusId() == 2 || postDTO.getPost().getStatus().getStatusId() == 4 ||
                        postDTO.getPost().getStatus().getStatusId() == 5) {
                    postService.updatePost(postDTO);
                    return new ResponseEntity<>(postDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //    ThuanNN
    @GetMapping("listPost/{count}")
    public ResponseEntity<Page<Post>> getAllPost(@PathVariable(name = "count") Integer count) {
        PageRequest pageable = PageRequest.of(0, count);
        if (postService.findAllNewest(pageable).isEmpty()) {
            return new ResponseEntity<>(postService.findAllNewest(pageable), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postService.findAllNewest(pageable), HttpStatus.OK);
    }

    //    ThuanNN
//    @PostMapping("createPost/{username}")
//    public ResponseEntity<Void> createPost(@PathVariable(name = "username") String username,
//                                           @RequestBody Post post) {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        post.setUser(user);
//        postService.save(username, post);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    /**
     * Author: ThuanNN, ViNTT
     * Save new post
     */
    @PostMapping("createPost")
    public ResponseEntity<Void> createPost(@RequestBody PostDTO postDTO) {
        try {
            Post post = postDTO.getPost();
            String username = postDTO.getUsername();
            postService.saveNewPost(post, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: ViNTT
     * Search basic by keyword in post title
     */
    @GetMapping("search")
    public ResponseEntity<List<Post>> searchByTitleContaining(@RequestParam(name = "key") String keyword) {
        List<Post> posts = postService.searchByTitleContaining(keyword);

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Author: ViNTT, ThuanNN
     * Search advance
     */
    @GetMapping("search-advance")
    public ResponseEntity<List<Post>> searchAdvance(@RequestParam(name = "key") String keyword,
                                                    @RequestParam(name = "category") String category,
                                                    @RequestParam(name = "province") String province) {
        List<Post> posts;

        if (category.equals("")) {
            if (province.equals("")) {
                posts = this.postService.searchByTitleContaining(keyword);
            } else {
                posts = this.postService.searchAdvanceWithProvince(keyword, province);
            }
        } else {
            if (province.equals("")) {
                posts = this.postService.searchAdvanceWithCategory(keyword, category);
            } else {
                posts = this.postService.searchAdvance(keyword, category, province);
            }
        }

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Author: ViNTT
     * Get post detail by post id
     */
    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer postId) {
        Post post = postService.findById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); // 200
    }

    /**
     * Author: ViNTT
     * Get data for Post Details Page
     */
    @GetMapping("{id}/active")
    public ResponseEntity<Post> getActivePostById(@PathVariable("id") Integer postId) {
        Post post = postService.findActivePostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(post, HttpStatus.OK); // 200
    }

    /**
     * Author: ViNTT
     * Get data for List Post By Category Page
     */
    @GetMapping("category/{category}")
    public ResponseEntity<Page<Post>> getActivePostsByCategoryName(@PathVariable("category") String categoryName,
                                                                   @PageableDefault(size = 2) Pageable pageable) {
        Page<Post> postList = postService.findAllActiveByCategoryName(categoryName, pageable);
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
    public ResponseEntity<Page<Post>> getActivePostsByCategoryNameAndChildCategoryName(
            @PathVariable("category") String categoryName,
            @PathVariable("childCategory") String childCategoryName,
            @PageableDefault(size = 2) Pageable pageable) {
        Page<Post> postList = postService.findAllActiveByCategoryNameAndChildCategoryName(categoryName, childCategoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * ThuanNN
     *
     * @param categoryName
     * @param count
     * @return ResponseEntity<Page < Post>>
     */
    @GetMapping("categories/{category}/{count}")
    public ResponseEntity<Page<Post>> getAllActivePostsByCategoryName(@PathVariable("category") String categoryName,
                                                                      @PathVariable(name = "count") Integer count) {
        PageRequest pageable = PageRequest.of(0, count);
        Page<Post> postList = postService.findAllActiveByCategoryName(categoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    /**
     * ThuanNN
     *
     * @param categoryName
     * @param childCategoryName
     * @param count
     * @return ResponseEntity<Page < Post>>
     */
    @GetMapping("categories/{category}/{childCategory}/{count}")
    public ResponseEntity<Page<Post>> getAllActivePostsByCategoryNameAndChildCategoryName(
            @PathVariable("category") String categoryName,
            @PathVariable("childCategory") String childCategoryName,
            @PathVariable(name = "count") Integer count) {
        PageRequest pageable = PageRequest.of(0, count);
        Page<Post> postList = postService.findAllActiveByCategoryNameAndChildCategoryName(categoryName, childCategoryName, pageable);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // method of Dong
    @GetMapping(value = "/list", params = {"page", "size", "onSorting", "textSorting"})
    public ResponseEntity<Page<Post>> listPost(@RequestParam("page") int page, @RequestParam("size") int size,
                                               @RequestParam("onSorting") boolean onSorting, @RequestParam("textSorting") String textSorting) {
        System.out.println(textSorting);
        try {
            Page<Post> postList;
            if (textSorting.equals("")) {
                postList = postService.findAllPost(PageRequest.of(page, size));
            } else {
                if (!onSorting) {
                    postList = postService.findAllPost(PageRequest.of(page, size, Sort.by(textSorting).ascending()));

                } else {
                    postList = postService.findAllPost(PageRequest.of(page, size, Sort.by(textSorting).descending()));
                }
            }
            if (postList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Post currentPost = postService.findById(id);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/statistic", params = {"startDate", "endDate"})
    public ResponseEntity<List<PostStatisticDTO>> getListStatisticQuantity(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        List<PostStatisticDTO> postList = postService.statisticQuantityPost(startDate, endDate);
        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        return new ResponseEntity<>(postList, HttpStatus.OK); //200
    }
}
