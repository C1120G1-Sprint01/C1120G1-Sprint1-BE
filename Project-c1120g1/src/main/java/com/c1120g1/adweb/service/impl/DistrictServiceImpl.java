package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.DistrictRepository;
import com.c1120g1.adweb.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository repository;
}
