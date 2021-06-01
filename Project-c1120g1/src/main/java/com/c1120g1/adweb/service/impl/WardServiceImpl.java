package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.repository.WardRepository;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository repository;


    @Override
    public List<Ward> getAllWard() {
        return repository.findAll();
    }
    @Override
    public List<Ward> findAllByDistrictId(int districtId) {
        return repository.findAllByDistrictId(districtId);

    }

    @Override
    public List<Ward> findWardByDistrictId(Integer wardId) {
        return repository.findByDistrictId(wardId);
    }
}
