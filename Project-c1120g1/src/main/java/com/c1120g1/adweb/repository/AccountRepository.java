package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByUsername(String username);
}
