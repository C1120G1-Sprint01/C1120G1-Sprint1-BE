package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Account;

public interface AccountService {

    Account findByUsername(String username);
}
