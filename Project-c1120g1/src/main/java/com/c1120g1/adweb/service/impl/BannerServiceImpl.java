package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.dto.BannerDTO;
import com.c1120g1.adweb.repository.BannerRepository;
import com.c1120g1.adweb.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository repository;

    /**
     * Method: get all banner
     * Authod: HanTH
     *
     * @return
     */
    @Override
    public List<Banner> showAllAdvertiseBanner() {
        return repository.showAllAdvertiseBanner();
    }

    /**
     * Method: get all banner by position
     * Author: HanTH
     *
     * @param positionId
     * @return
     */
    @Override
    public List<Banner> showAllBannerByPosition(Integer positionId) {
        return repository.showAllBannerByPosition( positionId );
    }

    /**
     * Method: add banner in list banner
     * Author: HanTH
     *
     * @param bannerDTO
     * @return
     */
    @Override
    public void addAdvertiseBanner(BannerDTO bannerDTO) {
        repository.addAdvertiseBanner( bannerDTO.getDuration(), bannerDTO.getImage(),
                bannerDTO.getPositionId(), bannerDTO.getSizeId() );
    }

    /**
     * Method: Edit banner in list banner
     * Author: HanTH
     *
     * @param bannerDTO
     * @return
     */
    @Override
    public void editAdvertiseBanner(BannerDTO bannerDTO) {
        repository.editAdvertiseBanner( bannerDTO.getBannerId(), bannerDTO.getDuration(), bannerDTO.getImage(),
                bannerDTO.getPositionId(), bannerDTO.getSizeId() );
    }

    /**
     * Method: Delete banner in list banner
     * Author: HanTH
     *
     * @param bannerId
     * @return
     */
    @Override
    public void deleteAdvertiseBanner(Integer bannerId) {
        repository.deleteAdvertiseBanner( bannerId );
    }

    /**
     * Method: get one banner in list banner
     * Author: HanTH
     *
     * @param bannerId
     * @return
     */
    @Override
    public Banner findBannerById(Integer bannerId) {
        return repository.findBannerById( bannerId );
    }
}
