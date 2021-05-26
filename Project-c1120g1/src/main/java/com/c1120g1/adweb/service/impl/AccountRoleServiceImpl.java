package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.repository.AccountRoleRepository;
import com.c1120g1.adweb.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountRoleRepository repository;
}
