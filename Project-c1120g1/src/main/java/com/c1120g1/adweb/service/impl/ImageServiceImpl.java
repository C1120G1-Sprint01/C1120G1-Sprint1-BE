package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Image;
import com.c1120g1.adweb.repository.ImageRepository;
import com.c1120g1.adweb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;

    //    ThuanNN
    @Override
    public void saveImage(String url) {
        repository.saveImage("image default", url);
    }

    //    ThuanNN
    @Override
    public Image findByUrl(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public void save(String url, Integer postId) {
        repository.save(url, postId);
    }

    @Override
    public void delete(Integer postId) {
        repository.delete(postId);
    }
}
