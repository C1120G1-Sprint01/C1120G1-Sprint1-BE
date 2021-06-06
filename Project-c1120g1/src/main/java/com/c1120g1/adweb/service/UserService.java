package com.c1120g1.adweb.service;

import com.c1120g1.adweb.dto.UserStatisticsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserStatisticsDTO> statisticUser(String startDate, String endDate);

}
