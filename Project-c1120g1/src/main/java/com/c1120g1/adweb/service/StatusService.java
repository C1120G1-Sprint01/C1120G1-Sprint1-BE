package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Status;

import java.util.List;

public interface StatusService {

    List<Status> findAll();

    Status findById(Integer id);
}
