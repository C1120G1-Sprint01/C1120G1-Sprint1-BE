package com.c1120g1.adweb.controller;

import com.c1120g1.adweb.entity.Status;
import com.c1120g1.adweb.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/status")
    public List<Status> getListStatus(){
        return statusService.findAll();
    }

}
