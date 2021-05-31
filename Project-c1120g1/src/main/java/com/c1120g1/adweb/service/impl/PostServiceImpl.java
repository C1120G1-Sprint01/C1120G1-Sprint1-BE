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

    @Override
    public Page<Post> findAllListDetail(Pageable pageable) {
        return repository.findAllListDetail(pageable);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null);
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
    public void save(Post post) {
        repository.save(post);
    }
}
