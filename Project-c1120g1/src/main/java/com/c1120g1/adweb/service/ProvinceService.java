package com.c1120g1.adweb.service;

import com.c1120g1.adweb.dto.ProvinceDTO;
import com.c1120g1.adweb.entity.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> findAll();

    List<ProvinceDTO> findAllDTO();
}
