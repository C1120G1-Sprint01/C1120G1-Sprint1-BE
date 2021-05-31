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
    private PostRepository repository;

    public Page<Post> findAllByUsername(String username, Pageable pageable) {
        return repository.findAllByUsername(username, pageable);
    }

    @Override
    public List<Post> findAllByCategoryName(String categoryName) {
        return repository.findAllByCategoryName(categoryName);
    }

    @Override
    public List<Post> findAllByCategoryNameAndChildCategoryName(String categoryName, String childCategoryName) {
        return repository.findAllByCategoryNameAndChildCategoryName(categoryName, childCategoryName);
    }

    @Override
    public Post findByIdAndUserId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
