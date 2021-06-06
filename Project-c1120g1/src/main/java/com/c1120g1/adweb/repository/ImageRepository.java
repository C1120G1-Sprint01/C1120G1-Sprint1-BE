package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer> {

//    ThuanNN
    @Query(value = "insert into image(image_name, url) " +
                    "value (?1, ?2)",
            nativeQuery = true)
    void saveImage(String imageName, String url);

    @Query(value =  "select * from image " +
                    "where url = ?1",
            nativeQuery = true)
    Image findByUrl(String url);
}
