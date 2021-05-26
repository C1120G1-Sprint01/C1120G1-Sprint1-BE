package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository repository;
}
