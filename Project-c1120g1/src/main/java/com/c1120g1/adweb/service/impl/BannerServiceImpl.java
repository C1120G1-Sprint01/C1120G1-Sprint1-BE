package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.BannerRepository;
import com.c1120g1.adweb.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository repository;
}
