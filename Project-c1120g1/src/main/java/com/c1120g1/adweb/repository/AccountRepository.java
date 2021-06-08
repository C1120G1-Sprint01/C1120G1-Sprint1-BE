package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByUsername(String username);

    @Modifying
    @Query(value = "insert into account (username, password, register_date) " +
            "values (:username, :password, :register_date)",
            nativeQuery = true)
    @Transactional
    void saveUserAccount(@Param("username")String username, @Param("password")String password, @Param("register_date")LocalDate register_date);

    @Query(value = "select * from `account` where username = ?1 ", nativeQuery = true)
    Account getAccountByUsername(String username);

    @Query(value = "SELECT u FROM Account u WHERE u.username = ?1")
    Account getUserById(String username);

    @Query(value = "update Account u set u.password = ?1 where u.username = ?2")
    void updatePasswordUser(String password);

    @Query(value = "select * from account where username = ?1", nativeQuery = true)
    Account checkUserExists(String username);

}
