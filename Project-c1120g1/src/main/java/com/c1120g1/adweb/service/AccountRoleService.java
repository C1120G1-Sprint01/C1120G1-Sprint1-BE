package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;

import java.util.List;

public interface AccountRoleService {
    List<AccountRole> findByAccount(Account account);
    AccountRole findAccountRoleByAccount(Account account);
    AccountRole findById(Integer accountRoleId);
    void saveAccountRoleUser(String username , Integer roleId);
}
