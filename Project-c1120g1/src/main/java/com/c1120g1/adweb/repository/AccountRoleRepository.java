package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {

    List<AccountRole> findByAccount(Account account);
}
