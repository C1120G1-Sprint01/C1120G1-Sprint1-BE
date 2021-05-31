package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.DistrictDTO;
import com.c1120g1.adweb.dto.ProvinceDTO;
import com.c1120g1.adweb.entity.Ward;
import com.c1120g1.adweb.service.DistrictService;
import com.c1120g1.adweb.service.ProvinceService;
import com.c1120g1.adweb.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtService;

    @Autowired
    WardService wardService;

    @GetMapping("/province")
    public ResponseEntity<List<ProvinceDTO>> getAllProvince() {
        List<ProvinceDTO> provinceDTOList = provinceService.findAll();

        if (provinceDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provinceDTOList, HttpStatus.OK);
    }

    @GetMapping("/district/{provinceId}")
    public ResponseEntity<List<DistrictDTO>> getAllDistrictByProvinceId(@PathVariable Integer provinceId) {
        List<DistrictDTO> districtDTOList = districtService.findAllByProvinceId(provinceId);

        if (districtDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districtDTOList, HttpStatus.OK);
    }

    @GetMapping("/ward/{districtId}")
    public ResponseEntity<List<Ward>> getAllWardByDistrictId(@PathVariable Integer districtId) {
        List<Ward> wardList = wardService.findAllByDistrictId(districtId);

        if(wardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wardList, HttpStatus.OK);
    }
}
