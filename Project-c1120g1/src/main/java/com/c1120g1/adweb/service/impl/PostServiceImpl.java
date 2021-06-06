package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.entity.Status;
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

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    public Page<Post> findAllByUsername(String username, Pageable pageable) {
        return repository.findAllByUsername(username, pageable);
    }

    @Override
    public Post findByIdAndUserId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Post> findAllNewest(Pageable pageable) {
        return repository.findAllNewest(pageable);
    }

    //    ThuanNN
    @Override
    public void save(String username, Post post) {
        String postDateTime = postService.getPostDateTime();
        post.setPostDateTime(postDateTime);
        Status status = statusService.findById(1);
        post.setStatus(status);

//        Set<Image> imagesSet = new HashSet<>();
//        if (post.getImageSet() == null){
//            Image img = new Image("img_default", "https://fakeimg.pl/100x100/?text=C1120G1");
//            imagesSet.add(img);
//            post.setImageSet(imagesSet);
//        }
        repository.save(post);
    }

    //    ThuanNN
    @Override
    public Page<Post> search(String keyword, Integer category, Integer province, Pageable pageable) {
        return repository.search(keyword, category, province, pageable);
    }

    //    ThuanNN
    @Override
    public String getPostDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return simpleDateFormat.format(now);
    }

    //    ViNTT
    @Override
    public Page<Post> findAllByCategoryName(String categoryName, Pageable pageable) {
        categoryName = categoryName.replace("-", " ");
        return repository.findAllByCategoryName(categoryName, pageable);
    }

    //    ViNTT
    @Override
    public Page<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName, Pageable pageable) {
        categoryName = categoryName.replace("-", " ");
        childCategoryName = childCategoryName.replace("-", " ");
        return repository.findAllByCategoryNameAndChildCategoryName(categoryName, childCategoryName, pageable);
    }
}
