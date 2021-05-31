package com.c1120g1.adweb.controller;
import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin/post/list")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "", params = {"page", "size", "onSorting", "textSorting"})
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

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Post currentPost = postService.findById(id);
        if (currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/admin/statistical")
//    public String statisticalByDatePost( @RequestParam(name = "start") String startDate,
//                               @RequestParam(name = "end") String endDate,
//                               Model model) {
//        List<Post> currentPost = postService.statisticalByDate(startDate, endDate);
//        if (currentPost == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        postService.statisticalByDate(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
