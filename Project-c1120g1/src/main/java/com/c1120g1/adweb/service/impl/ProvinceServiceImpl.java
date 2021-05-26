package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.ProvinceRepository;
import com.c1120g1.adweb.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository repository;
}
