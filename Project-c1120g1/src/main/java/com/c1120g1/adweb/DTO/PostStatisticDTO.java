package com.c1120g1.adweb.DTO;

import java.time.LocalDate;

public interface PostStatisticDTO {
    LocalDate getTimePost();
    int getCountPostSuccess();
    int getCountPostFailure();

}
