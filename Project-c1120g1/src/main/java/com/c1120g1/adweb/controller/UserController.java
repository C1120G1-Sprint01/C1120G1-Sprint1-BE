package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.dto.UserStatisticsDTO;
import com.c1120g1.adweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/statistic", params = {"startDate", "endDate"})
    public ResponseEntity<List<UserStatisticsDTO>> getListUserStatistic(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        List<UserStatisticsDTO> userList = userService.statisticUser(startDate, endDate);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
