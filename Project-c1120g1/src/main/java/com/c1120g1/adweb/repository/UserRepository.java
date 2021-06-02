package com.c1120g1.adweb.repository;

import com.c1120g1.adweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(value = "INSERT INTO `user` ( avatar_Url, email, name, phone, username) " +
            "values " + "(:avatarUrl "+":email," + ":name," + ":phone," + ":account,", nativeQuery = true)
    @Transactional
    void saveUser(   @Param("avatar")String avatarUrl,
                        @Param("name") String name,
                        @Param("account") String account,
                        @Param("email") String email,
                        @Param("phone") String phone);


    User findByEmail(String email);
}
