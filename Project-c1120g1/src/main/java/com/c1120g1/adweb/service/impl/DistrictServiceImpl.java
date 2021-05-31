package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.dto.DistrictDTO;
import com.c1120g1.adweb.entity.District;
import com.c1120g1.adweb.repository.DistrictRepository;
import com.c1120g1.adweb.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository repository;

    @Override
    public List<DistrictDTO> findAllByProvinceId(int provinceId) {
        List<District> districtList = repository.findAllByProvinceId(provinceId);
        List<DistrictDTO> districtDTOList = new ArrayList<>();
        for (District district : districtList) {
            DistrictDTO districtDTO = new DistrictDTO();
            districtDTO.setDistrictId(district.getDistrictId());
            districtDTO.setDistrictName(district.getDistrictName());
            districtDTOList.add(districtDTO);
        }

        return districtDTOList;
    }

//    @Override
//    public List<District> findAllByProvinceId(int provinceId) {
//        return repository.findAllByProvinceId(provinceId);
//    }
}
