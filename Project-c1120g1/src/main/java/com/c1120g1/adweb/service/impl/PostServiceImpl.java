package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.repository.PostRepository;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

//    @Override
//    public List<Post> statisticalByDate(String startDate, String endDate) {
//        return postRepository.statisticalByDate(startDate, endDate);
//    }
}
