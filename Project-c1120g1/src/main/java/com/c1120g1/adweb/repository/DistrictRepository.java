package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {




    @Query(value = "select * from district where district.province_id = :provinceId", nativeQuery = true)
    List<District> findAllByProvinceId(int provinceId);


//  Ngoc - find district by province id
    @Query(value = "select d from District d join Province p on p.provinceId = d.province.provinceId where p.provinceId =?1")
    List<District> findAllDistrictByProvinceId(Integer provinceId);





}
