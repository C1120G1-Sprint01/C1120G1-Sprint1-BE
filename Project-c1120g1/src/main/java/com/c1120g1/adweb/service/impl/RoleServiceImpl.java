package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.RoleRepository;
import com.c1120g1.adweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;
}
