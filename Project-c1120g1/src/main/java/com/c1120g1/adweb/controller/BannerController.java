package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.entity.Position;
import com.c1120g1.adweb.entity.Size;
import com.c1120g1.adweb.service.BannerService;
import com.c1120g1.adweb.service.PositionService;
import com.c1120g1.adweb.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private SizeService sizeService;

    @GetMapping("admin/banner")
    public ResponseEntity<?> showAllAdvertiseBanner() {
        try {
            List<Banner> listBanner = bannerService.showAllAdvertiseBanner();
            return new ResponseEntity<>( listBanner, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping(value = "admin/banner/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAdvertiseBanner(@RequestBody Banner banner) {
        try {
            bannerService.addAdvertiseBanner( banner );
            return new ResponseEntity<>( HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    @GetMapping("/admin/banner/position")
    public ResponseEntity<?> showAllPosition() {
        try {
            List<Position> positionList = positionService.showAllPosition();
            return new ResponseEntity<>( positionList, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/admin/banner/size")
    public ResponseEntity<?> showAllSize() {
        try {
            List<Size> sizeList = sizeService.showAllSize();
            return new ResponseEntity<>( sizeList, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
    @GetMapping("/admin/banner/{bannerId}")
    public ResponseEntity<?> findBannerById(@PathVariable Integer bannerId){
        try {
            Banner banner = bannerService.findBannerById( bannerId );
            return new ResponseEntity<>( banner,HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/admin/banner/delete/{bannerId}")
    public ResponseEntity<?> deleteAdvertiseBanner(@PathVariable Integer bannerId){
        try {
            bannerService.deleteAdvertiseBanner( bannerId );
            return new ResponseEntity<>( HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
    @PutMapping("/admin/banner/edit")
    public ResponseEntity<?> editAdvertiseBanner(@RequestBody Banner banner){
        try {
            bannerService.editAdvertiseBanner( banner );
            return new ResponseEntity<>( HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

}
