package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.Account;
import com.c1120g1.adweb.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 45fb757551547e0c3f079cc29a14566456c8840e

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {

    List<AccountRole> findByAccount(Account account);
}
