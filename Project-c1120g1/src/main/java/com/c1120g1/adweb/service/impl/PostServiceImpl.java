package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Image;
import com.c1120g1.adweb.entity.Post;
import com.c1120g1.adweb.repository.ImageRepository;
import com.c1120g1.adweb.repository.PostRepository;
import com.c1120g1.adweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ImageRepository imageRepository;

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
    public void updatePost(Post post) {
        repository.updatePost(post.getDescription(), post.getEmail(), post.getPhone(), post.isPostType(),
                post.getPosterName(), post.getPrice(), post.getTitle(), post.getChildCategory().getChildCategoryId(),
                post.getStatus().getStatusId(), post.getWard().getWardId(), post.getPostId());

//        for (Image image : post.getImageSet()) {
//            imageRepository.update(image.getUrl(), image.getImageId());
//        }
    }
}
