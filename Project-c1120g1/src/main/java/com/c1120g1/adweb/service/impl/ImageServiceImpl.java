package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.ImageRepository;
import com.c1120g1.adweb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;
}
