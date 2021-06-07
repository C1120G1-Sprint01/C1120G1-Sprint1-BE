package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Image;

public interface ImageService {

    void saveImage(String url);

    Image findByUrl(String url);

    void save(String url, Integer postId);

    void delete(Integer postId);
}
