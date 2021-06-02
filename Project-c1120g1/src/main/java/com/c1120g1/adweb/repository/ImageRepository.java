package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update image " +
            "set url = ?1 where image_id = ?2", nativeQuery = true)
    void update(String url, Integer imageId);
}
