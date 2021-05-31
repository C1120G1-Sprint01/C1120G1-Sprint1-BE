package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Ward;


import java.util.List;

public interface WardService {
    
    List<Ward> findAllByDistrictId(Integer districtId);

    List<Ward> findWardByDistrictId(Integer districtId);
}
