package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("admin/banner")
    public ResponseEntity<?> showAllAdvertiseBanner() {
        try {
            List<Banner> listBanner = bannerService.showAllAdvertiseBanner();
            return new ResponseEntity<>( listBanner, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
    @PostMapping("admin/banner/add")
    public ResponseEntity<?> addAdvertiseBanner(@RequestBody Banner banner){
        try {
            bannerService.addAdvertiseBanner( banner );
            return new ResponseEntity<>( HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
}
