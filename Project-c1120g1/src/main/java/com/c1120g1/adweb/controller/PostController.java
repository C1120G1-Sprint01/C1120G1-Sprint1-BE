package com.c1120g1.adweb.controller;
import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("listPost")
    public ResponseEntity<Page<Post>> getAllPost(@PageableDefault(size = 4) Pageable pageable) {
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
    @GetMapping(value = "/list", params = {"page", "size", "onSorting", "textSorting"})
    public ResponseEntity<Page<Post>> listEmployee(@RequestParam("page") int page, @RequestParam("size") int size,
                                                   @RequestParam("onSorting") boolean onSorting, @RequestParam("textSorting") String textSorting) {
        System.out.println(textSorting);
        try {
            Page<Post> postList;
            if (textSorting.equals("")) {
                postList = postService.findAll(PageRequest.of(page, size));
            } else {
                if (!onSorting) {
                    postList = postService.findAll(PageRequest.of(page, size, Sort.by(textSorting).ascending()));

                } else {
                    postList = postService.findAll(PageRequest.of(page, size, Sort.by(textSorting).descending()));
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

    @GetMapping("/list/{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
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
}
