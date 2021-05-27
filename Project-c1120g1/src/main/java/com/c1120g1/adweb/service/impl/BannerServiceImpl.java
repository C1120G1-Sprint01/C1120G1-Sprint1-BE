package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Banner;
import com.c1120g1.adweb.repository.BannerRepository;
import com.c1120g1.adweb.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository repository;

    @Override
    public List<Banner> showAllAdvertiseBanner() {
        return repository.showAllAdvertiseBanner();
    }

    @Override
    public void addAdvertiseBanner(Banner banner) {
        repository.addAdvertiseBanner( banner.getDuration(),banner.getImage(),banner.getPosition(),banner.getSize() );
    }
}
