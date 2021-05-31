package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.dto.ProvinceDTO;
import com.c1120g1.adweb.entity.Province;
import com.c1120g1.adweb.repository.ProvinceRepository;
import com.c1120g1.adweb.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository repository;

    @Override
    public List<ProvinceDTO> findAll() {
        List<Province> provinceList = repository.findAll();
        List<ProvinceDTO> provinceDTOList = new ArrayList<>();
        ProvinceDTO provinceDTO = null;
        for (Province province : provinceList) {
            provinceDTO = new ProvinceDTO();
            provinceDTO.setProvinceId(province.getProvinceId());
            provinceDTO.setProvinceName(province.getProvinceName());
            provinceDTOList.add(provinceDTO);
        }

        return provinceDTOList;
    }

}
