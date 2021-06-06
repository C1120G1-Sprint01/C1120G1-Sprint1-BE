package com.c1120g1.adweb.dto;

import com.c1120g1.adweb.entity.User;

import java.time.LocalDate;

public interface UserStatisticsDTO {

    String getTimeRegister();
    int getCountNewUser();

}
