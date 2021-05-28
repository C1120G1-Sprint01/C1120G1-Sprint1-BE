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
    @Query(value = "INSERT INTO `user` ( avatar_Url, email, name, phone, username, ward_id) " +
            "values " + "(:avatar_Url "+":email," + ":name," + ":phone," + ":username," , nativeQuery = true)
    @Transactional
    void saveUser(   @Param("avatar")String avatarUrl,
                        @Param("name") String name,
                        @Param("username") String username,
                        @Param("email") String email,
                        @Param("phone") String phone);

}
