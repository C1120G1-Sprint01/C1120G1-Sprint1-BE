package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {


    @Query(value = "select * from ward where ward.district_id = :districtId", nativeQuery = true)
    List<Ward> findAllByDistrictId(int districtId);



//    ngoc
    @Query(value = "select w from Ward w join District d on d.districtId = w.district.districtId where d.districtId =?1")
    List<Ward> findByDistrictId(Integer districtId);



    @Query(value = "select * from ward where ward.district_id = :districtId", nativeQuery = true)
    List<Ward> findAllByDistrictId(Integer districtId);

}
