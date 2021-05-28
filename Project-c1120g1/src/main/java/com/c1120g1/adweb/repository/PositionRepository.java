package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {

    @Query(value = "SELECT * FROM ad_web_db.position" ,nativeQuery = true)
    List<Position> showAllPosition();
}
