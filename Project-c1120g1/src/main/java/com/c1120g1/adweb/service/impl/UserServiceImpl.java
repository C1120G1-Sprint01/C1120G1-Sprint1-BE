package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.dto.UserStatisticsDTO;
import com.c1120g1.adweb.repository.UserRepository;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserStatisticsDTO> statisticUser(String startDate, String endDate) {
        return repository.userStatistics(startDate,endDate);
    }

}
