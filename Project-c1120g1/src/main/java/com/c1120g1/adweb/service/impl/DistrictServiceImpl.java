package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.District;
import com.c1120g1.adweb.repository.DistrictRepository;
import com.c1120g1.adweb.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository repository;

    //    ThuanNN: edit return null
    @Override
    public List<District> findAllDistrictByProvinceId(Integer provinceId) {
        return repository.findAllByProvinceId(provinceId);
    }

    //    ThuanNN: edit return null
    @Override
    public List<District> findAllByProvinceId(Integer provinceId) {
        return repository.findAllByProvinceId(provinceId);
    }
}
