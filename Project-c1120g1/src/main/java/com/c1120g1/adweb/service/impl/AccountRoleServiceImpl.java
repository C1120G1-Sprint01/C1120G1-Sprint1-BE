package com.c1120g1.adweb.service.impl;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;
import com.c1120g1.adweb.entity.User;
import com.c1120g1.adweb.repository.AccountRoleRepository;
import com.c1120g1.adweb.repository.RoleRepository;
import com.c1120g1.adweb.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountRoleRepository repository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<AccountRole> findByAccount(Account account) {
        return repository.findByAccount(account);
    }

    @Override
    public AccountRole findById(Integer accountRoleId) {
        return repository.findById(accountRoleId).orElse(null);
    }

    @Override
    public void saveAccountRoleUser(String username, Integer roleId) {
        repository.saveAccountRole(username, roleId);
    }


    @Override
    public AccountRole findAccountRoleByAccount(Account account) {
        return repository.findAccountRoleByAccount(account);
    }
}
