package com.c1120g1.adweb.dto;

import java.time.LocalDate;

public interface PostStatisticDTO {
    LocalDate getTimePost();
    int getCountPostSuccess();
    int getCountPostFailure();

}
