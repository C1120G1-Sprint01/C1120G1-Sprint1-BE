package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Position;
import com.c1120g1.adweb.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "SELECT * FROM ad_web_db.size" ,nativeQuery = true)
    List<Size> showAllSize();
}
