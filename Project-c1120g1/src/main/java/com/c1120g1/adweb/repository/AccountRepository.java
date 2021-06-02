package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT u FROM Account u WHERE u.username = ?1")
    Account getUserById(String username);

    @Query(value = "update Account u set u.password = ?1 where u.username = ?2")
    void updatePasswordUser(String password);

    Account findByUsername(String username);


}
