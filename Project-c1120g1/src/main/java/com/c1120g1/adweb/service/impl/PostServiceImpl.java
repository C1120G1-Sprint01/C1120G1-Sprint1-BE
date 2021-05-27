package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.repository.PostRepository;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
