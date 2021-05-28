package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.District;
import com.c1120g1.adweb.entity.Province;
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
    public ResponseEntity<List<Province>> getAllProvince() {
        List<Province> provinceList = provinceService.findAll();

        if (provinceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provinceList, HttpStatus.OK);
    }

    @GetMapping("/district/{provinceId}")
    public ResponseEntity<List<District>> getAllDistrictByProvinceId(@PathVariable Integer provinceId) {
        List<District> districtList = districtService.findAllByProvinceId(provinceId);

        if (districtList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(districtList, HttpStatus.OK);
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