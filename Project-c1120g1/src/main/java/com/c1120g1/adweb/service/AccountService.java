package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Account;


import java.util.List;

public interface AccountService {
    void saveAccount(Account account);

    List<Account> getAllAccount();


    List<Account> findAllAccount();
    void save(Account account);


    Account findByUsername(String username);

    Account checkUserExists(String username);

    String generateCode();


    void sendEmailApprove(String email, String code);


    void sendEmail(String email, String code);

}
