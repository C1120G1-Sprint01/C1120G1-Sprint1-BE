package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "select * from ward where ward.district_id = :districtId", nativeQuery = true)
    List<Ward> findAllByDistrictId(Integer districtId);
}
