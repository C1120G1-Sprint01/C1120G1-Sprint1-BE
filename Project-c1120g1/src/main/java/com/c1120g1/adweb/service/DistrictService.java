package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.District;

import java.util.List;

public interface DistrictService {

    List<District> findAllByProvinceId(int provinceId);

    List<District> findAllDistrictByProvinceId(Integer provinceId);
}
