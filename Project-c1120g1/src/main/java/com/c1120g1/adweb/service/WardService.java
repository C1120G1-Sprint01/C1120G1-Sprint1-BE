package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Ward;


import java.util.List;

public interface WardService {
    List<Ward> getAllWard();

    List<Ward> findAllByDistrictId(int districtId);

    
    List<Ward> findAllByDistrictId(Integer districtId);

    List<Ward> findWardByDistrictId(Integer districtId);

}
