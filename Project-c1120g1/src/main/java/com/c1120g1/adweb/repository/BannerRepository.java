package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
    @Query(value = "SELECT * FROM ad_web_db.banner", nativeQuery = true)
    List<Banner> showAllAdvertiseBanner();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ad_web_db.banner (banner.duration, banner.image, banner.position, banner.size)\n" +
            "VALUE(?1,?2,?3,?4)", nativeQuery = true)
    void addAdvertiseBanner(Timestamp duration, String banner, String position, String size);
}
