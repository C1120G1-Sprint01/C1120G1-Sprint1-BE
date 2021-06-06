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

    //    ThuanNN: edit return null
    @Override
    public List<Ward> findAllByDistrictId(Integer districtId) {
        return repository.findAllByDistrictId(districtId);
    }

    //    ThuanNN: edit return null
    @Override
    public List<Ward> findWardByDistrictId(Integer districtId) {
        return repository.findAllByDistrictId(districtId);
    }
}
