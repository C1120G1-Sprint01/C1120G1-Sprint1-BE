package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.dto.UserStatisticsDTO;
import com.c1120g1.adweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT register_date as timeRegister, " +
            "COUNT(case when register_date >= :startDate and register_date <= :endDate then 1 end ) as countNewUser " +
            "FROM account " +
            "GROUP BY register_date " +
            "HAVING date(register_date) between :startDate and :endDate " +
            "ORDER BY date(register_date)"
            , nativeQuery = true)
    List<UserStatisticsDTO> userStatistics(String startDate, String endDate);



}
