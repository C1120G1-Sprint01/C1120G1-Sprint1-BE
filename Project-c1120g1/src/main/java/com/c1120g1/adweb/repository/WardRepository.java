package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {


    @Query(value = "select * from ward where ward.district_id = :districtId", nativeQuery = true)
    List<Ward> findAllByDistrictId(int districtId);

//    @Query("select d from District d join Province p on p.id = d.province.id where p.id =?1")
//    List<District> findByProvinceId(Long id);

    @Query(value = "select w from Ward w join District d on d.districtId = w.district.districtId where d.districtId =?1")
    List<Ward> findByDistrictId(Integer districtId);
}
