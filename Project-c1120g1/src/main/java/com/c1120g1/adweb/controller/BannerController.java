package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.entity.Position;
import com.c1120g1.adweb.entity.Size;
import com.c1120g1.adweb.DTO.BannerDTO;
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

    /**
     * Method: get all banner
     * Author: HanTH
     *
     * @return
     */
    @GetMapping("admin/banner")
    public ResponseEntity<?> showAllAdvertiseBanner() {
        try {
            List<Banner> listBanner = bannerService.showAllAdvertiseBanner();
            return new ResponseEntity<>( listBanner, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: get all banner by position
     * Author: HanTH
     *
     * @param positionId
     * @return
     */
    @GetMapping("admin/banner/position/{positionId}")
    public ResponseEntity<?> showAllAdvertiseBannerByPosition(@PathVariable Integer positionId) {
        try {
            List<Banner> listBanner = bannerService.showAllBannerByPosition( positionId );
            return new ResponseEntity<>( listBanner, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: add banner in list banner
     * Author: HanTH
     *
     * @param bannerDTO
     * @return
     */
    @PostMapping(value = "admin/banner/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAdvertiseBanner(@RequestBody BannerDTO bannerDTO) {
        try {
            bannerService.addAdvertiseBanner( bannerDTO );
            return new ResponseEntity<>( HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Method: get all position
     * Author: HanTH
     *
     * @return
     */
    @GetMapping("/admin/banner/position")
    public ResponseEntity<?> showAllPosition() {
        try {
            List<Position> positionList = positionService.showAllPosition();
            return new ResponseEntity<>( positionList, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: get all size
     * Author: HanTH
     *
     * @return
     */
    @GetMapping("/admin/banner/size")
    public ResponseEntity<?> showAllSize() {
        try {
            List<Size> sizeList = sizeService.showAllSize();
            return new ResponseEntity<>( sizeList, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: get one banner in list banner
     * Author: HanTH
     *
     * @param bannerId
     * @return
     */
    @GetMapping("/admin/banner/{bannerId}")
    public ResponseEntity<?> findBannerById(@PathVariable Integer bannerId) {
        try {
            Banner banner = bannerService.findBannerById( bannerId );
            return new ResponseEntity<>( banner, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: Delete banner in list banner
     * Author: HanTH
     *
     * @param bannerId
     * @return
     */
    @DeleteMapping("/admin/banner/delete/{bannerId}")
    public ResponseEntity<?> deleteAdvertiseBanner(@PathVariable Integer bannerId) {
        try {
            bannerService.deleteAdvertiseBanner( bannerId );
            return new ResponseEntity<>( HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: Edit banner in list banner
     * Author: HanTH
     *
     * @param bannerDTO
     * @return
     */
    @PutMapping("/admin/banner/edit")
    public ResponseEntity<?> editAdvertiseBanner(@RequestBody BannerDTO bannerDTO) {
        try {
            bannerService.editAdvertiseBanner( bannerDTO );
            return new ResponseEntity<>( HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

}
