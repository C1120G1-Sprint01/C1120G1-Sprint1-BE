package com.c1120g1.adweb.service;

import com.c1120g1.adweb.entity.Account;


import java.util.List;

public interface AccountService {
    List<Account> findAllAccount();

    void save(Account account);

    void saveAccount(Account account);

    List<Account> getAllAccount();


    Account findByUsername(String username);

    String generateCode();

    void saveUserAccount (Account account);

    Boolean checkPassword(Account account , String password);

    void setNewPassword(Account account, String newPassword);

    Account getAccountByUsername (String username);

    void sendEmailApprove(String email, String code);

    void sendEmail(String email, String code);

    Account checkUserExists(String username);

}
