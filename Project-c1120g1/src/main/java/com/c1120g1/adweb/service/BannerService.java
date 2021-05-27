package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Banner;

import java.sql.Timestamp;
import java.util.List;

public interface BannerService {
    List<Banner> showAllAdvertiseBanner();

    void addAdvertiseBanner(Banner banner);
}
