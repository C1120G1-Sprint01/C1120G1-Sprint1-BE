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
    @Query(value = "INSERT INTO ad_web_db.banner (banner.duration, banner.image, banner.position_id, banner.size_id)\n" +
            "VALUE(?1,?2,?3,?4)", nativeQuery = true)
    void addAdvertiseBanner(Timestamp duration, String image, Integer positionId, Integer sizeId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ad_web_db.banner SET banner.duration = ?2, banner.image = ?3, banner.position_id = ?4, banner.size_id = ?5\n" +
            "WHERE banner.banner_id = ?1", nativeQuery = true)
    void editAdvertiseBanner(Integer bannerId, Timestamp duration, String image, Integer positionId, Integer sizeId);

    @Modifying
    @Query(value = "DELETE ad_web_db.banner WHERE banner.banner_id = ?1" ,nativeQuery = true)
    void deleteAdvertiseBanner(Integer bannerId);

    @Query(value = "SELECT * FROM ad_web_db.banner WHERE banner.banner_id = ?1" ,nativeQuery = true)
    Banner findBannerById(Integer bannerId);

    @Query(value = "SELECT * FROM ad_web_db.banner WHERE banner.banner_id = ?1",nativeQuery = true)
    List<Banner> showAllBannerByPosition(Integer positionId);
}
