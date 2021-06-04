
//package com.c1120g1.adweb.service.impl;
//
//import com.c1120g1.adweb.entity.Post;
//
//
//import com.c1120g1.adweb.repository.PostRepository;
//import com.c1120g1.adweb.service.PostService;
//import com.c1120g1.adweb.service.StatusService;
//import com.c1120g1.adweb.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class PostServiceImpl implements PostService {
//
//    @Autowired
//    private PostRepository repository;
//
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private StatusService statusService;
//
//    @Override
//    public Page<Post> findAllListDetail(Pageable pageable) {
//        return repository.findAllListDetail(pageable);
//    }
//
//    @Override
//    public Post findById(Integer id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    @Override
//    public Post findByIdAndUserId(Integer id) {
//        return repository.findById(id).orElse(null);
//    }
//
//
//    @Override
//    public Page<Post> findAllListApprove(Pageable pageable) {
//        return repository.findAllListApprove(pageable);
//    }
//
//    @Override
//    public void approvePost(Integer id) {
//        repository.approvePost(id);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public void waitPost(Integer id) {
//        repository.waitPost(id);
//    }
//
//    @Override
//    public Page<Post> findAllListWait(Pageable pageable) {
//        return repository.findAllListWait(pageable);
//    }
//
//    @Override
//    public Page<Post> findAllByUsername(String username, Pageable pageable) {
//        return repository.findAllByUsername(username, pageable);
//    }
//
//    @Override
//    public Page<Post> findAllNewest(Pageable pageable) {
//        return repository.findAllNewest(pageable);
//    }
//
//    @Override
//    public void save(Post post) {
////        String postDateTime = postService.getPostDateTime();
////        System.out.println(postDateTime);
////
////        User user = userService.findById(1);
////        System.out.println(user);
////        post.setUser(user);
////
////        Status status = statusService.findById(1);
////        post.setStatus(status);
////
////        post.setPostDateTime(postDateTime);
////
////        repository.save(post);
//    }
//
//    @Override
//    public List<Post> search(String title, String child_category, String province_name) {
//        return repository.search(title, child_category, province_name);
//    }
//
//    @Override
//    public String getPostDateTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date now = new Date();
//        return simpleDateFormat.format(now);
//    }
//}

package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Post;

import com.c1120g1.adweb.repository.ImageRepository;
import com.c1120g1.adweb.entity.Status;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.PostRepository;
import com.c1120g1.adweb.service.PostService;
import com.c1120g1.adweb.service.StatusService;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private StatusService statusService;

    /**
     * author: ThinhTHB
     * method: search post by name
     * */
    @Override
    public List<Post> searchByName(String posterName) {
        return repository.searchByName(posterName);
    }

    @Override
    public Page<Post> findAllByUsername(String username, Pageable pageable) {
        return repository.findAllByUsername(username, pageable);
    }

    @Override
    public Page<Post> findAllListDetail(Pageable pageable) {
        return repository.findAllListDetail(pageable);
    }

    @Override
    public void cancelApprovePost(Integer id) {
        repository.cancelApprovePost(id);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post findByIdAndUserId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updatePost(Post post) {

        repository.updatePost(post.getDescription(),
                post.getEmail(),
                post.getPhone(),
                post.isPostType(),
                post.getPosterName(),
                post.getPrice(),
                post.getTitle(),
                post.getChildCategory().getChildCategoryId(),
                post.getStatus().getStatusId(),
                post.getWard().getWardId(), post.getPostId());

//        for (Image image : post.getImageSet()) {
//            imageRepository.update(image.getUrl(), image.getImageId());
//        }
    }

    @Override
    public Page<Post> findAllListApprove(Pageable pageable) {
        return repository.findAllListApprove(pageable);
    }

    @Override
    public void approvePost(Integer id) {
        repository.approvePost(id);
    }

    @Override
    public void deletePost(Integer id) {
        repository.deletePost(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void waitPost(Integer id) {
        repository.waitPost(id);
    }

    @Override
    public Page<Post> findAllListWait(Pageable pageable) {
        return repository.findAllListWait(pageable);
    }

    @Override
    public Page<Post> findAllNewest(Pageable pageable) {
        return repository.findAllNewest(pageable);
    }

    @Override
    public void save(Post post) {
        String postDateTime = postService.getPostDateTime();
        System.out.println(postDateTime);

        User user = userService.findById(1);
        System.out.println(user);
        post.setUser(user);

        Status status = statusService.findById(1);
        post.setStatus(status);

        post.setPostDateTime(postDateTime);

        repository.save(post);
    }

    @Override
    public List<Post> search(String title, String child_category, String province_name) {
        return repository.search(title, child_category, province_name);
    }

    @Override
    public String getPostDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now = new Date();
        return simpleDateFormat.format(now);


    }

    @Override
    public Page<Post> findAllByUsernameAndStatusId(String username, Integer statusId, Pageable pageable) {
        return repository.findAllByUsernameAndStatusId(username, statusId, pageable);
    }
}

