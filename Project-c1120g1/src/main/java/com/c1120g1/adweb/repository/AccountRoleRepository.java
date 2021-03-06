package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {

    List<AccountRole> findByAccount(Account account);

    AccountRole findAccountRoleByAccount(Account account);

    @Transactional
    @Modifying
    @Query(value = "insert into account_role (account_role.username,account_role.role_id) values (?1,?2) ",nativeQuery = true)
    void saveAccountRole(String username , Integer roleId);
}
