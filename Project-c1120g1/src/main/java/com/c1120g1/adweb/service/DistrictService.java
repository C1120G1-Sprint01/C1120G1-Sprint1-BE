package com.c1120g1.adweb.service;

import com.c1120g1.adweb.dto.DistrictDTO;
import com.c1120g1.adweb.entity.District;

import java.util.List;

public interface DistrictService {

    List<DistrictDTO> findAllByProvinceIdDTO(int provinceId);

    List<District> findAllDistrictByProvinceId(Integer provinceId);

    List<District> findAllByProvinceId(Integer provinceId);

}
