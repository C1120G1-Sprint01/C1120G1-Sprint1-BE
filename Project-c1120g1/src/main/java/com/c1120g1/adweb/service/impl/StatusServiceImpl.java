package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Status;
import com.c1120g1.adweb.repository.StatusRepository;
import com.c1120g1.adweb.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;

    @Override
    public List<Status> findAll() {
        return repository.findAll();
    }
}
