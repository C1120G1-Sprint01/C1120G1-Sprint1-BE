package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.DTO.BannerDTO;
import java.util.List;

public interface BannerService {

    List<Banner> showAllAdvertiseBanner();

    List<Banner> showAllBannerByPosition(Integer positionId);

    void addAdvertiseBanner(BannerDTO bannerDTO);

    void editAdvertiseBanner(BannerDTO bannerDTO);

    void deleteAdvertiseBanner(Integer bannerId);

    Banner findBannerById(Integer bannerId);
}
