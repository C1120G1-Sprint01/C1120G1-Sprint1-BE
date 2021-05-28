package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query(value = "select w from Ward w where w.wardId=?1")
    Ward findWardById();
}
