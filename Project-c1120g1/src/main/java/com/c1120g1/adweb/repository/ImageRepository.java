package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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

    @Modifying
    @Query(value = "insert into image (url,post_id)" +
            "values (?1, ?2)", nativeQuery = true)
    void save(String url, Integer postId);

    @Modifying
    @Query(value = "delete from image where post_id = ?1", nativeQuery = true)
    void delete(Integer postId);
}
